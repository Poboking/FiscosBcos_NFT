package org.sziit.app.biz.artwork.collection;


import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sziit.app.biz.artwork.dto.collection.CollectionQueryReqDto;
import org.sziit.app.biz.vo.collection.MyHoldCollectionVo;
import org.sziit.infrastructure.common.NftConstants;
import org.sziit.infrastructure.common.PageResult;
import org.sziit.infrastructure.dao.domain.CollectionEntity;
import org.sziit.infrastructure.dao.domain.MemberHoldCollectionEntity;
import org.sziit.infrastructure.dao.domain.MemberResaleCollectionEntity;
import org.sziit.infrastructure.repository.impl.CollectionRepositoryImpl;
import org.sziit.infrastructure.repository.impl.MemberHoldCollectionRepositoryImpl;
import org.sziit.infrastructure.repository.impl.MemberResaleCollectionRepositoryImpl;

import javax.annotation.Resource;
import java.util.List;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/9 20:41
 */
@Service
@AllArgsConstructor
public class MemberResaleCollectionService {
    @Resource
    private MemberResaleCollectionRepositoryImpl memberResaleCollectionRepository;
    @Resource
    private CollectionRepositoryImpl collectionRepository;

    public PageResult<MemberResaleCollectionEntity> getOrderDescPageList(long current, long pageSize) {
        IPage<MemberResaleCollectionEntity> pageResult = memberResaleCollectionRepository.getPriceOrderDescPageList(current, pageSize);
        return PageResult.convertFor(pageResult, pageSize);
    }

    public PageResult<MemberResaleCollectionEntity> getOrderAscPageList(long current, long pageSize) {
        IPage<MemberResaleCollectionEntity> pageResult = memberResaleCollectionRepository.getPriceOrderAscPageList(current, pageSize);
        return PageResult.convertFor(pageResult, pageSize);
    }

    public PageResult<MemberResaleCollectionEntity> getPageListByCollectionQueryParam(CollectionQueryReqDto reqDto) {
        if (reqDto.isOrderDesc()) {
            return PageResult.convertFor(memberResaleCollectionRepository.getPriceOrderDescPageListByCreatorIdAndCollectionId(
                    reqDto.getCurrent(), reqDto.getPageSize(), reqDto.getCreatorId(), reqDto.getCollectionId()), reqDto.getPageSize());
        } else {
            return PageResult.convertFor(memberResaleCollectionRepository.getPriceOrderAscPageListByCreatorIdAndCollectionId(
                    reqDto.getCurrent(), reqDto.getPageSize(), reqDto.getCreatorId(), reqDto.getCollectionId()), reqDto.getPageSize());
        }
    }

    public PageResult<MyHoldCollectionVo> getMySoldCollectionPageList(long current, long pageSize, String memberId) {
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
        return PageResult.convertFor(pageSoldEntity, pageSize, MyHoldCollectionVo.convertFor(records));
    }
}
