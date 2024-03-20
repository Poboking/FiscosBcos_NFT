package org.sziit.app.biz.artwork.collection;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sziit.app.biz.artwork.dto.collection.CollectionDetailRespDTO;
import org.sziit.app.biz.artwork.dto.collection.CollectionIntroRespDTO;
import org.sziit.app.biz.artwork.dto.creator.CreatorRespDTO;
import org.sziit.app.biz.convert.artwork.CollectionConvert;
import org.sziit.app.biz.convert.artwork.CreatorConvert;
import org.sziit.infrastructure.common.PageResult;
import org.sziit.infrastructure.dao.domain.CollectionEntity;
import org.sziit.infrastructure.repository.impl.CollectionRepositoryImpl;
import org.sziit.infrastructure.repository.impl.CreatorRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

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

    public CollectionDetailRespDTO getCollectionDetail(String collectionId) {
        return CollectionConvert.INSTANCE.convertToDetailRespDTO(collectionRepository.getCollectionDetail(collectionId));
    }


    public CreatorRespDTO getCreatorById(String creatorId) {
        return CreatorConvert.INSTANCE.convertToRespDTO(creatorRepository.getById(creatorId));
    }

    public PageResult<CollectionEntity> getPageListByCreatorId(long current, long pageSize, String creatorId) {
        return PageResult.convertFor(collectionRepository.getPageListByCreatorId(current, pageSize, creatorId), pageSize);
    }

    public PageResult<CollectionIntroRespDTO> getIntroPageList(long current, long pageSize) {
        IPage<CollectionEntity> pageEntity = collectionRepository.getPageList(current, pageSize);
        List<CollectionIntroRespDTO> resultList = new ArrayList<>();
        pageEntity.getRecords().forEach(bean -> {
            CreatorRespDTO creator = getCreatorById(bean.getCreatorId());
            bean.setCreatorName(creator.getName());
            bean.setCreatorAvatar(creator.getAvatar());
            resultList.add(CollectionConvert.INSTANCE.convertToIntroRespDTO(bean));
        });
        return PageResult.convertFor(pageEntity, pageSize, resultList);
    }

    public PageResult<CollectionIntroRespDTO> getIntroPageListByCreatorId(long current, long pageSize, String creatorId) {
        IPage<CollectionEntity> pageEntity = collectionRepository.getPageListByCreatorId(current, pageSize, creatorId);
        List<CollectionIntroRespDTO> resultList = new ArrayList<>();
        pageEntity.getRecords().forEach(bean -> {
            CreatorRespDTO creator = getCreatorById(creatorId);
            bean.setCreatorName(creator.getName());
            bean.setCreatorAvatar(creator.getAvatar());
            resultList.add(CollectionConvert.INSTANCE.convertToIntroRespDTO(bean));
        });
        return PageResult.convertFor(pageEntity, pageSize, resultList);
    }
}
