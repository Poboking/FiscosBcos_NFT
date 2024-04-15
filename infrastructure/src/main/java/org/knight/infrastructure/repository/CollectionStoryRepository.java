package org.knight.infrastructure.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import org.knight.infrastructure.dao.domain.CollectionStoryEntity;

import java.util.List;

/**
 * @author poboking
 * @description 针对表【collection_story】的数据库操作Service
 * @createDate 2024-03-07 17:31:43
 */
public interface CollectionStoryRepository extends IService<CollectionStoryEntity> {

    /**
     * 根据收藏品ID获取收藏故事列表
     *
     * @param id 收藏品ID
     * @return 收藏故事列表
     */
    List<CollectionStoryEntity> getListByCollectionId(String id);

    /**
     * 根据收藏品ID删除收藏故事
     *
     * @param collectionId 收藏品ID
     * @return 是否删除成功
     */
    Boolean deleteByCollectionId(String collectionId);
}
