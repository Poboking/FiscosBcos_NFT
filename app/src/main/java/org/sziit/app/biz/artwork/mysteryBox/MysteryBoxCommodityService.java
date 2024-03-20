package org.sziit.app.biz.artwork.mysteryBox;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sziit.app.biz.artwork.dto.collection.CollectionQueryReqDTO;
import org.sziit.infrastructure.common.PageResult;
import org.sziit.infrastructure.dao.domain.MysteryBoxCommodityEntity;
import org.sziit.infrastructure.repository.impl.MysteryBoxCommodityRepositoryImpl;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/8 23:09
 */
@Service
@AllArgsConstructor
public class MysteryBoxCommodityService {
    @Autowired
    private MysteryBoxCommodityRepositoryImpl mysteryBoxCommodityRepository;

    public PageResult<MysteryBoxCommodityEntity> getPageList(long current, long pageSize) {
        IPage<MysteryBoxCommodityEntity> pageList = mysteryBoxCommodityRepository.getPageList(current, pageSize);
        return PageResult.convertFor(pageList, pageSize);
    }

    public PageResult<MysteryBoxCommodityEntity> getPageList(long current, long pageSize, String memberId) {
        IPage<MysteryBoxCommodityEntity> pageList = mysteryBoxCommodityRepository.getPageListByMemberId(current, pageSize, memberId);
        return PageResult.convertFor(pageList, pageSize);
    }

    public PageResult<MysteryBoxCommodityEntity> getPageList(long current, long pageSize, String name, String memberId) {
        IPage<MysteryBoxCommodityEntity> pageList = mysteryBoxCommodityRepository.getPageListByMemberIdAndName(current, pageSize, name, memberId);
        return PageResult.convertFor(pageList, pageSize);
    }

    public PageResult<MysteryBoxCommodityEntity> getPageListByCollectionQueryParam(CollectionQueryReqDTO reqDto) {
        if (reqDto.isOrderDesc()) {
            IPage<MysteryBoxCommodityEntity> pageEntity = mysteryBoxCommodityRepository.getPriceOrderDescPageListByCreatorIdAndCollectionId(reqDto.getCurrent(), reqDto.getPageSize(),
                    reqDto.getCreatorId(), reqDto.getCollectionId());
            return PageResult.convertFor(pageEntity, reqDto.getPageSize());
        } else {
            IPage<MysteryBoxCommodityEntity> pageEntity = mysteryBoxCommodityRepository.getPriceOrderAscPageListByCreatorIdAndCollectionId(reqDto.getCurrent(), reqDto.getPageSize(),
                    reqDto.getCreatorId(), reqDto.getCollectionId());
            return PageResult.convertFor(pageEntity, reqDto.getPageSize());
        }
    }

    public PageResult<MysteryBoxCommodityEntity> getPageListByCreatorId(long current, long pageSize, String creatorId) {
        return PageResult.convertFor(mysteryBoxCommodityRepository
                .getPriceOrderDescPageListByCreatorId(current, pageSize, creatorId), pageSize);
    }
}
