package org.knight.app.biz.artwork.collection;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.knight.app.biz.artwork.dto.holdcollection.MemberHoldCollectionDetailRespDTO;
import org.knight.app.biz.artwork.dto.holdcollection.MemberHoldCollectionRespDTO;
import org.knight.app.biz.artwork.dto.holdcollection.MyHoldCollectionDetailRespDTO;
import org.knight.app.biz.artwork.dto.holdcollection.MyHoldCollectionRespDTO;
import org.knight.app.biz.convert.artwork.MemberHoldCollectionConvert;
import org.knight.app.biz.convert.artwork.MyHoldCollectionConvert;
import org.knight.app.biz.exception.collection.CollectionNotFoundException;
import org.knight.app.biz.exception.collection.HoldCollectionDetailFailedException;
import org.knight.app.biz.exception.collection.ResaleCollectionDetailFailedException;
import org.knight.app.biz.exception.member.MemberNotFoundException;
import org.knight.infrastructure.common.NftConstants;
import org.knight.infrastructure.common.PageResult;
import org.knight.infrastructure.dao.domain.*;
import org.knight.infrastructure.repository.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/8 20:48
 */
@Service
public class MemberCollectionService {
    private MemberHoldCollectionRepositoryImpl memberHoldCollectionRepository;
    private final CollectionRepositoryImpl collectionRepository;
    private final MemberRepositoryImpl memberRepository;
    private final IssuedCollectionRepositoryImpl issuedCollectionRepository;

    private final CreatorRepositoryImpl creatorRepository;
    @Autowired
    public MemberCollectionService(MemberHoldCollectionRepositoryImpl memberHoldCollectionRepository, CollectionRepositoryImpl collectionRepository, MemberRepositoryImpl memberRepository, IssuedCollectionRepositoryImpl issuedCollectionRepository, CreatorRepositoryImpl creatorRepository) {
        this.memberHoldCollectionRepository = memberHoldCollectionRepository;
        this.collectionRepository = collectionRepository;
        this.memberRepository = memberRepository;
        this.issuedCollectionRepository = issuedCollectionRepository;
        this.creatorRepository = creatorRepository;
    }

    public PageResult<MemberHoldCollectionEntity> getPageList(long current, long pageSize, String memberId) {
        IPage<MemberHoldCollectionEntity> pageResult = memberHoldCollectionRepository.getPageListByMemberId(current, pageSize, memberId);
        return PageResult.convertFor(pageResult, pageSize);
    }

    public PageResult<MemberHoldCollectionEntity> getPageList(long current, long pageSize, String name, String memberId) {
        IPage<MemberHoldCollectionEntity> pageResult = memberHoldCollectionRepository.getPageListByMemberIdAndName(current, pageSize, name, memberId);
        return PageResult.convertFor(pageResult, pageSize);
    }

    public PageResult<MyHoldCollectionRespDTO> getMyHoldCollectionPageList(long current, long pageSize, String memberId) {
        IPage<CollectionEntity> pageEntity = collectionRepository.getPageListByCommodityType(current, pageSize, NftConstants.商品类型_藏品);
        IPage<MemberHoldCollectionEntity> pageHoldEntity = memberHoldCollectionRepository.getPageListByMemberId(current, pageSize, memberId);
        List<MemberHoldCollectionEntity> records = pageHoldEntity.getRecords();
        List<MyHoldCollectionRespDTO> respDTOs = new ArrayList<>();
        records.forEach(e -> {
            pageEntity.getRecords().forEach(c -> {
                if (e.getCollectionId().equals(c.getId())) {
                    e.setName(Optional.ofNullable(c.getName()).orElse("DataError: Unknown Collection Name"));
                    e.setCover(Optional.ofNullable(c.getCover()).orElse("DataError: Unknown Collection Cover"));
                }
            });
            if (CharSequenceUtil.isBlank(e.getName())) {
                e.setName("DataError: Unknown Collection Name");
            }
            if (CharSequenceUtil.isBlank(e.getCover())) {
                e.setCover("DataError: Unknown Collection Cover");
            }
            MyHoldCollectionRespDTO respDTO = MyHoldCollectionConvert.convertToRespDTO(e);
            respDTO.setId(e.getId());
            respDTO.setHoldTime(DateUtil.format(e.getHoldTime(), NftConstants.DATE_FORMAT));
            respDTOs.add(respDTO);
        });
        return PageResult.convertFor(pageHoldEntity, pageSize, respDTOs);
    }

    public MemberHoldCollectionDetailRespDTO getHoldCollectionDetail(String holdCollectionId) {
        if (CharSequenceUtil.isBlank(holdCollectionId)) {
            throw new CollectionNotFoundException(CharSequenceUtil.format("{}:resaleCollectionId is null", holdCollectionId));
        }
        try {
            MemberHoldCollectionEntity holdCollection = memberHoldCollectionRepository.getById(holdCollectionId);
            CollectionEntity collection = collectionRepository.getById(holdCollection.getCollectionId());
            IssuedCollectionEntity issuedCollection = issuedCollectionRepository.getById(holdCollection.getIssuedCollectionId());
            MemberEntity holdMember = memberRepository.getById(holdCollection.getMemberId());
            Long quantity = Optional.of(issuedCollectionRepository.count(new QueryWrapper<IssuedCollectionEntity>().eq("collection_id", collection.getId()))).orElse(1L);
            CreatorEntity creator = creatorRepository.getById(collection.getCreatorId());
            return MemberHoldCollectionDetailRespDTO.builder()
                    .id(holdCollection.getId())
                    .issuedCollectionId(holdCollection.getIssuedCollectionId())
                    // TODO: 2024/4/6 此处的交易Hash需要修改 具体代码待实现
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
                    .resalePrice(holdCollection.getPrice())
                    .build();
        } catch (Exception e) {
            throw new HoldCollectionDetailFailedException("获取持有藏品详情失败");
        }
    }


    public MyHoldCollectionDetailRespDTO getMyHoldCollectionDetail(String collectionId, String memberId) {
        if (CharSequenceUtil.isBlank(collectionId)) {
            throw new CollectionNotFoundException(CharSequenceUtil.format("{}:collectionId is null", collectionId));
        }
        if (CharSequenceUtil.isBlank(memberId)) {
            throw new MemberNotFoundException(CharSequenceUtil.format("{}:memberId is null", memberId));
        }
        MemberHoldCollectionEntity holdCollection = memberHoldCollectionRepository.getOne(new QueryWrapper<MemberHoldCollectionEntity>()
                .eq("collection_id", collectionId)
                .eq("member_id", memberId));
        if (Objects.isNull(holdCollection)){
            throw new CollectionNotFoundException(CharSequenceUtil.format("{}:holdCollection is null", collectionId));
        }
        MemberHoldCollectionDetailRespDTO respDTO = getHoldCollectionDetail(holdCollection.getId());
        return MemberHoldCollectionConvert.INSTANCE.convertToDetailRespDTO(respDTO);
    }

    public PageResult<MemberHoldCollectionRespDTO> findMemberHoldCollectionByPage(Long current, Long pageSize, String memberMobile, String collectionName, String state, String gainWay) {
        String memberId = memberRepository.getIdByMobile(memberMobile);
        String collectionId = collectionRepository.getIdByName(collectionName);
        if (CharSequenceUtil.isBlank(memberId)) {
            throw new MemberNotFoundException("会员不存在");
        }
        if (CharSequenceUtil.isBlank(collectionId)) {
            throw new CollectionNotFoundException("藏品不存在");
        }
        IPage<MemberHoldCollectionEntity> pageEntity = memberHoldCollectionRepository.getPageListByParam(current, pageSize, memberId, collectionName, state, gainWay);
        return PageResult.convertFor(pageEntity, pageSize, MemberHoldCollectionConvert.INSTANCE.convertToRespDTO(pageEntity.getRecords()));
    }

}