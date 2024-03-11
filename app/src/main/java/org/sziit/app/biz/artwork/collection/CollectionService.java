package org.sziit.app.biz.artwork.collection;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sziit.app.biz.artwork.dto.collection.CollectionDetailRespDto;
import org.sziit.app.biz.artwork.dto.creator.CreatorRespDto;
import org.sziit.app.biz.convert.artwork.CollectionConvert;
import org.sziit.app.biz.convert.artwork.CreatorConvert;
import org.sziit.infrastructure.common.PageResult;
import org.sziit.infrastructure.dao.domain.CollectionEntity;
import org.sziit.infrastructure.repository.impl.CollectionRepositoryImpl;
import org.sziit.infrastructure.repository.impl.CreatorRepositoryImpl;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/8 19:53
 */
@Service
@AllArgsConstructor
@SuppressWarnings("all")
public class CollectionService {
    @Autowired
    private CollectionRepositoryImpl collectionRepository;
    @Autowired
    private CreatorRepositoryImpl creatorRepository;


    public PageResult<CollectionEntity> getPageList(long current, long pageSize) {
        IPage<CollectionEntity> pageResult = collectionRepository.getPageList(current, pageSize);
        return PageResult.convertFor(pageResult, pageSize);
    }

    public PageResult<CollectionEntity> getPageListByName(long current, long pageSize, String name) {
        IPage<CollectionEntity> pageResult = collectionRepository.getPageListByName(current, pageSize, name);
        return PageResult.convertFor(pageResult, pageSize);
    }

    public CollectionDetailRespDto getCollectionDetail(String collectionId) {
        return CollectionConvert.INSTANCE.convert(collectionRepository.getCollectionDetail(collectionId));
    }

    public CreatorRespDto getCreatorById(String collectionId) {
        return CreatorConvert.INSTANCE.convert(creatorRepository.getById(collectionId));
    }

    public PageResult<CollectionEntity> getPageListByCreatorId(long current, long pageSize, String creatorId) {
        return PageResult.convertFor(collectionRepository.getPageListByCreatorId(current, pageSize, creatorId), pageSize);
    }
}
