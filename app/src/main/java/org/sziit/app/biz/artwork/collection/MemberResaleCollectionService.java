package org.sziit.app.biz.artwork.collection;


import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sziit.app.biz.artwork.dto.collection.CollectionQueryReqDTO;
import org.sziit.app.biz.artwork.dto.collection.CollectionResaleRespDTO;
import org.sziit.app.biz.artwork.dto.myholdcollection.MyHoldCollectionRespDTO;
import org.sziit.app.biz.convert.artwork.CollectionConvert;
import org.sziit.app.biz.convert.artwork.MyHoldCollectionConvert;
import org.sziit.infrastructure.common.NftConstants;
import org.sziit.infrastructure.common.PageResult;
import org.sziit.infrastructure.dao.domain.CollectionEntity;
import org.sziit.infrastructure.dao.domain.MemberResaleCollectionEntity;
import org.sziit.infrastructure.repository.impl.CollectionRepositoryImpl;
import org.sziit.infrastructure.repository.impl.IssuedCollectionRepositoryImpl;
import org.sziit.infrastructure.repository.impl.MemberResaleCollectionRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/9 20:41
 */
@Service
@AllArgsConstructor
public class MemberResaleCollectionService {
    @Autowired
    private MemberResaleCollectionRepositoryImpl memberResaleCollectionRepository;
    @Autowired
    private CollectionRepositoryImpl collectionRepository;
    @Autowired
    private IssuedCollectionRepositoryImpl issuedCollectionRepository;

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

    public PageResult<MyHoldCollectionRespDTO> getMySoldCollectionPageList(long current, long pageSize, String memberId) {
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
        return PageResult.convertFor(pageSoldEntity, pageSize, MyHoldCollectionConvert.convertToRespDTO(records));
    }
}
