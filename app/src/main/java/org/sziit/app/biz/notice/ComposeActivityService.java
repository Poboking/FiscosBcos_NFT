package org.sziit.app.biz.notice;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sziit.app.biz.convert.notice.activity.ComposeActivityConvert;
import org.sziit.app.biz.convert.notice.activity.ComposeMaterialConvert;
import org.sziit.app.biz.notice.dto.activity.ComposeActivityDetailRespDTO;
import org.sziit.app.biz.notice.dto.activity.ComposeActivityRespDTO;
import org.sziit.infrastructure.dao.domain.CollectionEntity;
import org.sziit.infrastructure.dao.domain.ComposeActivityEntity;
import org.sziit.infrastructure.dao.domain.ComposeMaterialEntity;
import org.sziit.infrastructure.repository.impl.CollectionRepositoryImpl;
import org.sziit.infrastructure.repository.impl.ComposeActivityRepositoryImpl;
import org.sziit.infrastructure.repository.impl.ComposeMaterialRepositoryImpl;

import java.util.List;
import java.util.Optional;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/17 19:46
 */
@Service
@AllArgsConstructor
public class ComposeActivityService {
    @Autowired
    private ComposeActivityRepositoryImpl composeActivityRepository;
    @Autowired
    private ComposeMaterialRepositoryImpl composeMaterialRepository;
    @Autowired
    private CollectionRepositoryImpl collectionRepository;

    public ComposeActivityRespDTO getComposeActivityById(String id) {
        return ComposeActivityConvert.INSTANCE.convertToRespDTO(composeActivityRepository.getComposeActivity(id));
    }

    public ComposeActivityRespDTO getComposeActivityByTitle(String title) {
        return ComposeActivityConvert.INSTANCE.convertToRespDTO(composeActivityRepository.getComposeActivity(title));
    }

    public List<ComposeActivityRespDTO> getComposeActivityList() {
        return ComposeActivityConvert.INSTANCE.convertToRespDTO(composeActivityRepository.getComposeActivityList());
    }

    public ComposeActivityDetailRespDTO getComposeActivityDetail(String id) {
        ComposeActivityEntity composeActivity = composeActivityRepository.getComposeActivity(id);
        List<ComposeMaterialEntity> materilas = composeMaterialRepository.list(new QueryWrapper<ComposeMaterialEntity>()
                .eq(Optional.ofNullable(id).isPresent(), "activity_id", id));
        CollectionEntity collectionEntity = collectionRepository.getById(composeActivity.getCollectionId());
        return ComposeActivityDetailRespDTO.builder()
                .id(composeActivity.getId())
                .title(composeActivity.getTitle())
                .collectionName(collectionEntity.getName())
                .collectionCover(collectionEntity.getCover())
                .materials(ComposeMaterialConvert.INSTANCE.convertToRespDTO(materilas))
                .build();
    }
}
