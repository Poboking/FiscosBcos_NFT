package org.knight.app.biz.artwork.collection;


import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.knight.app.biz.artwork.dto.collection.CollectionQueryReqDTO;
import org.knight.app.biz.artwork.dto.collection.CollectionResaleDetailRespDTO;
import org.knight.app.biz.artwork.dto.collection.CollectionResaleRespDTO;
import org.knight.app.biz.artwork.dto.holdcollection.MyResaleCollectionRespDTO;
import org.knight.app.biz.artwork.dto.holdcollection.MySaleCollectionRespDTO;
import org.knight.app.biz.convert.artwork.CollectionConvert;
import org.knight.app.biz.convert.artwork.MyResaleCollectionConvert;
import org.knight.app.biz.convert.artwork.MySaleCollectionConvert;
import org.knight.app.biz.exception.collection.CollectionNotFoundException;
import org.knight.app.biz.exception.collection.ResaleCollectionDetailFailedException;
import org.knight.infrastructure.common.NftConstants;
import org.knight.infrastructure.common.PageResult;
import org.knight.infrastructure.dao.domain.*;
import org.knight.infrastructure.repository.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/9 20:41
 */
@Service
public class MemberResaleCollectionService {
    private MemberResaleCollectionRepositoryImpl memberResaleCollectionRepository;
    private CollectionRepositoryImpl collectionRepository;
    private IssuedCollectionRepositoryImpl issuedCollectionRepository;

    private MemberRepositoryImpl memberRepository;

    private CreatorRepositoryImpl creatorRepository;

    @Autowired
    public MemberResaleCollectionService(MemberResaleCollectionRepositoryImpl memberResaleCollectionRepository, CollectionRepositoryImpl collectionRepository, IssuedCollectionRepositoryImpl issuedCollectionRepository) {
        this.memberResaleCollectionRepository = memberResaleCollectionRepository;
        this.collectionRepository = collectionRepository;
        this.issuedCollectionRepository = issuedCollectionRepository;
    }

    public PageResult<MemberResaleCollectionEntity> getOrderDescPageList(long current, long pageSize) {
        IPage<MemberResaleCollectionEntity> pageResult = memberResaleCollectionRepository.getPriceOrderDescPageList(current, pageSize);
        return PageResult.convertFor(pageResult, pageSize);
    }

    public PageResult<MemberResaleCollectionEntity> getOrderAscPageList(long current, long pageSize) {
        IPage<MemberResaleCollectionEntity> pageResult = memberResaleCollectionRepository.getPriceOrderAscPageList(current, pageSize);
        return PageResult.convertFor(pageResult, pageSize);
    }

    public PageResult<CollectionResaleRespDTO> getPageListByCollectionQueryParam(CollectionQueryReqDTO reqDto) {
        List<CollectionResaleRespDTO> resultList = new ArrayList<>();
        List<String> Ids = collectionRepository.getIdsByCommodityType(reqDto.getCommodityType());
        if (reqDto.isOrderDesc()) {
            IPage<MemberResaleCollectionEntity> entityIPage = memberResaleCollectionRepository.getPriceOrderDescPageListByParam(
                    reqDto.getCurrent(), reqDto.getPageSize(), reqDto.getCreatorId(), reqDto.getCollectionId(), NftConstants.持有藏品状态_转售中, Ids);
            entityIPage.getRecords().forEach(bean -> {
                CollectionResaleRespDTO resultBean = CollectionConvert.INSTANCE.convertToResaleRespDTO(bean);
                resultBean.setCollectionSerialNumber(issuedCollectionRepository
                        .getById(bean.getIssuedCollectionId())
                        .getCollectionSerialNumber());
                resultList.add(resultBean);
            });
            return PageResult.convertFor(entityIPage, reqDto.getPageSize(), resultList);
        } else {
            IPage<MemberResaleCollectionEntity> entityIPage = memberResaleCollectionRepository.getPriceOrderAscPageListByParam(
                    reqDto.getCurrent(), reqDto.getPageSize(), reqDto.getCreatorId(), reqDto.getCollectionId(), NftConstants.持有藏品状态_转售中, Ids);
            entityIPage.getRecords().forEach(bean -> {
                CollectionResaleRespDTO resultBean = CollectionConvert.INSTANCE.convertToResaleRespDTO(bean);
                resultBean.setCollectionSerialNumber(issuedCollectionRepository
                        .getById(bean.getIssuedCollectionId())
                        .getCollectionSerialNumber());
                resultList.add(resultBean);
            });
            return PageResult.convertFor(entityIPage, reqDto.getPageSize(), resultList);
        }
    }

    public PageResult<MyResaleCollectionRespDTO> getMyResaleCollectionPageList(long current, long pageSize, String memberId) {
        IPage<CollectionEntity> pageEntity = collectionRepository.getPageListByCommodityType(current, pageSize, NftConstants.商品类型_藏品);
        IPage<MemberResaleCollectionEntity> pageSoldEntity = memberResaleCollectionRepository.getPageListByMemberIdAndStatus(current, pageSize, memberId, NftConstants.持有藏品状态_转售中);
        List<MemberResaleCollectionEntity> records = pageSoldEntity.getRecords();
        records.forEach(e -> {
            pageEntity.getRecords().forEach(c -> {
                if (e.getCollectionId().equals(c.getId())) {
                    e.setName(c.getName());
                    e.setCover(c.getCover());
                }
            });
        });
        return PageResult.convertFor(pageSoldEntity, pageSize, MyResaleCollectionConvert.INSTANCE.convertToRespDTO(records));
    }

    public PageResult<MySaleCollectionRespDTO> getMySoldCollectionPageList(long current, long pageSize, String memberId) {
        IPage<CollectionEntity> pageEntity = collectionRepository.getPageListByCommodityType(current, pageSize, NftConstants.商品类型_藏品);
        IPage<MemberResaleCollectionEntity> pageSoldEntity = memberResaleCollectionRepository.getPageListByMemberIdAndStatus(current, pageSize, memberId, NftConstants.持有藏品状态_已卖出);
        List<MemberResaleCollectionEntity> records = pageSoldEntity.getRecords();
        records.forEach(e -> {
            pageEntity.getRecords().forEach(c -> {
                if (e.getCollectionId().equals(c.getId())) {
                    e.setName(c.getName());
                    e.setCover(c.getCover());
                }
            });
        });
        return PageResult.convertFor(pageSoldEntity, pageSize, MySaleCollectionConvert.INSTANCE.convertToRespDTO(records));
    }


    public CollectionResaleDetailRespDTO getCollectionDetail(String resaleCollectionId) {
        if (resaleCollectionId.isEmpty() || Boolean.FALSE.equals(collectionRepository.checkExist(resaleCollectionId))){
            throw new CollectionNotFoundException(CharSequenceUtil.format("{}:resaleCollectionId is null", resaleCollectionId));
        }
        try {
            MemberResaleCollectionEntity resaleCollection = memberResaleCollectionRepository.getById(resaleCollectionId);
            CollectionEntity collection = collectionRepository.getById(resaleCollection.getCollectionId());
            IssuedCollectionEntity issuedCollection = issuedCollectionRepository.getById(resaleCollection.getIssuedCollectionId());
            MemberEntity holdMember = memberRepository.getById(resaleCollection.getMemberId());
            Long quantity = Optional.of(issuedCollectionRepository.count(new QueryWrapper<IssuedCollectionEntity>().eq("collection_id", collection.getId()))).orElse(1L);
            CreatorEntity creator = creatorRepository.getById(collection.getCreatorId());
            return CollectionResaleDetailRespDTO.builder()
                    .id(resaleCollection.getId())
                    .issuedCollectionId(resaleCollection.getIssuedCollectionId())
                    .transactionHash("null")
                    .collectionHash(collection.getCollectionHash())
                    .holderBlockChainAddr(String.valueOf(holdMember.getBlockChainAddr()))
                    .holderNickName(holdMember.getNickName())
                    .holderAvatar(holdMember.getAvatar())
                    .collectionName(collection.getName())
                    .collectionCover(collection.getCover())
                    .commodityType(collection.getCommodityType())
                    .creatorId(collection.getCreatorId())
                    .creatorName(creator.getName())
                    .creatorAvatar(creator.getAvatar())
                    .uniqueId(issuedCollection.getUniqueId())
                    .quantity(quantity.intValue())
                    .collectionSerialNumber(issuedCollection.getCollectionSerialNumber())
                    .resalePrice(resaleCollection.getResalePrice())
                    .build();
        } catch (Exception e) {
            throw new ResaleCollectionDetailFailedException("获取转售藏品详情失败");
        }
    }
}
