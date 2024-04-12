package org.knight.app.biz.artwork.collection;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import org.knight.app.biz.artwork.dto.holdcollection.MemberHoldCollectionDetailRespDTO;
import org.knight.app.biz.artwork.dto.holdcollection.MemberHoldCollectionRespDTO;
import org.knight.app.biz.artwork.dto.holdcollection.MyHoldCollectionDetailRespDTO;
import org.knight.app.biz.artwork.dto.holdcollection.MyHoldCollectionRespDTO;
import org.knight.app.biz.convert.artwork.MemberHoldCollectionConvert;
import org.knight.app.biz.convert.artwork.MyHoldCollectionConvert;
import org.knight.app.biz.exception.collection.CollectionNotFoundException;
import org.knight.app.biz.exception.collection.HoldCollectionNotFoundException;
import org.knight.app.biz.exception.collection.IssuedCollectionNotFoundException;
import org.knight.app.biz.exception.member.CreatorNotFoundException;
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
        PageInfo<MemberHoldCollectionEntity> pageResult = memberHoldCollectionRepository.getPageListByMemberId(current, pageSize, memberId);
        return PageResult.convertFor(pageResult, pageSize);
    }

    public PageResult<MemberHoldCollectionEntity> getPageList(long current, long pageSize, String name, String memberId) {
        PageInfo<MemberHoldCollectionEntity> pageResult = memberHoldCollectionRepository.getPageListByMemberIdAndName(current, pageSize, name, memberId);
        return PageResult.convertFor(pageResult, pageSize);
    }

    public PageResult<MyHoldCollectionRespDTO> getMyHoldCollectionPageList(long current, long pageSize, String memberId) {
        PageInfo<CollectionEntity> pageEntity = collectionRepository.getPageListByCommodityType(current, pageSize, NftConstants.商品类型_藏品);
        List<String> collectionIds = new ArrayList<>();
        pageEntity.getList().forEach(c -> collectionIds.add(c.getId()));
        PageInfo<MemberHoldCollectionEntity> pageHoldEntity = memberHoldCollectionRepository.getHoldPageListByIdsAndMemberId(current, pageSize, collectionIds, memberId);
        List<MyHoldCollectionRespDTO> respDTOs = new ArrayList<>();
        pageHoldEntity.getList().forEach(e -> {
            pageEntity.getList().forEach(c -> {
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
            // TODO: 2024/4/11 这里的id需要修改
            respDTO.setId(e.getId());
            respDTO.setHoldTime(DateUtil.format(e.getHoldTime(), NftConstants.DATE_FORMAT));
            respDTOs.add(respDTO);
        });
        return PageResult.convertFor(pageHoldEntity, pageSize, respDTOs);
    }

    public MemberHoldCollectionDetailRespDTO getHoldCollectionDetail(String holdCollectionId) {
        if (CharSequenceUtil.isBlank(holdCollectionId)) {
            throw new CollectionNotFoundException(CharSequenceUtil.format("{}:获取持有藏品详情失败 as a result of holdCollectionId is null", holdCollectionId));
        }
        MemberHoldCollectionEntity holdCollection = memberHoldCollectionRepository.getById(holdCollectionId);
        if (Objects.isNull(holdCollection)) {
            throw new HoldCollectionNotFoundException(CharSequenceUtil.format("{}:获取持有藏品详情失败 as a result of holdCollection not found", holdCollectionId));
        }
        CollectionEntity collection = collectionRepository.getById(holdCollection.getCollectionId());
        if (Objects.isNull(collection)) {
            throw new CollectionNotFoundException(CharSequenceUtil.format("{}:获取持有藏品详情失败 as a result of collection not found", holdCollection.getCollectionId()));
        }
        // TODO: 2024/4/7 待实现发行藏品机制
        // TODO: 2024/4/10 或许得先删除多余的逻辑校验, 以及多余的异常处理
        IssuedCollectionEntity issuedCollection = issuedCollectionRepository.getById(holdCollection.getIssuedCollectionId());
        if (Objects.isNull(issuedCollection)) {
            throw new IssuedCollectionNotFoundException(CharSequenceUtil.format("{}:获取持有藏品详情失败 as a result of issuedCollection not found", holdCollection.getIssuedCollectionId()));
        }
        MemberEntity holdMember = memberRepository.getById(holdCollection.getMemberId());
        if (Objects.isNull(holdMember)) {
            throw new MemberNotFoundException(CharSequenceUtil.format("{}:获取持有藏品详情失败 as a result of holdMember not found", holdCollection.getMemberId()));
        }
        CreatorEntity creator = creatorRepository.getById(collection.getCreatorId());
        if (Objects.isNull(creator)) {
            throw new CreatorNotFoundException(CharSequenceUtil.format("{}:获取持有藏品详情失败 as a result of creator not found", collection.getCreatorId()));
        }
        Long quantity = Optional.of(issuedCollectionRepository.count(new QueryWrapper<IssuedCollectionEntity>().eq("collection_id", collection.getId()))).orElse(1L);
        return MemberHoldCollectionDetailRespDTO.builder()
                // TODO: 2024/4/11 这里的id需要修改
                .id(holdCollection.getCollectionId())
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
                .uniqueId(Optional.ofNullable(issuedCollection).get().getUniqueId())
                .quantity(quantity.intValue())
                .collectionSerialNumber(issuedCollection.getCollectionSerialNumber())
                .resalePrice(holdCollection.getPrice())
                .build();
    }


    public MyHoldCollectionDetailRespDTO getMyHoldCollectionDetail(String holdCollectionId, String memberId) {
        if (CharSequenceUtil.isBlank(holdCollectionId)) {
            throw new HoldCollectionNotFoundException(CharSequenceUtil.format("{}:holdCollectionId is null", holdCollectionId));
        }
        if (CharSequenceUtil.isBlank(memberId)) {
            throw new MemberNotFoundException(CharSequenceUtil.format("{}:memberId is null", memberId));
        }
        MemberHoldCollectionEntity holdCollection = memberHoldCollectionRepository.getById(holdCollectionId);
        if (Objects.isNull(holdCollection)) {
            throw new HoldCollectionNotFoundException(CharSequenceUtil.format("{}:holdCollection is null", holdCollectionId));
        }
        MemberHoldCollectionDetailRespDTO respDTO = getHoldCollectionDetail(holdCollection.getId());
        return MemberHoldCollectionConvert.INSTANCE.convertToDetailRespDTO(respDTO);
    }

    public PageResult<MemberHoldCollectionRespDTO> findMemberHoldCollectionByPage(Long current, Long pageSize, String memberMobile, String collectionName, String state, String gainWay) {
        String memberId = null;
        String collectionId = null;
        if (!CharSequenceUtil.isBlank(memberMobile)) {
            memberId = memberRepository.getIdByMobile(memberMobile);
            if (CharSequenceUtil.isBlank(memberId)) {
                throw new MemberNotFoundException("会员不存在");
            }
        }
        if (!CharSequenceUtil.isBlank(collectionName)) {
            collectionId = collectionRepository.getIdByName(collectionName);
            if (CharSequenceUtil.isBlank(collectionId)) {
                throw new CollectionNotFoundException("藏品不存在");
            }
        }
        PageInfo<MemberHoldCollectionEntity> pageEntity = memberHoldCollectionRepository.getPageListByParam(current, pageSize, memberId, collectionId, state, gainWay);
        return PageResult.convertFor(pageEntity, pageSize, MemberHoldCollectionConvert.INSTANCE.convertToRespDTO(pageEntity.getList()));
    }

}