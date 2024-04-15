package org.knight.infrastructure.repository.impl;

import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.knight.infrastructure.dao.domain.CollectionStoryEntity;
import org.knight.infrastructure.dao.mapper.CollectionStoryMapper;
import org.knight.infrastructure.repository.CollectionStoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author poboking
 * @description 针对表【collection_story】的数据库操作Service实现
 * @createDate 2024-03-07 17:31:43
 */
@Service
public class CollectionStoryRepositoryImpl extends ServiceImpl<CollectionStoryMapper, CollectionStoryEntity>
        implements CollectionStoryRepository {

    private final CollectionStoryMapper collectionStoryMapper;

    @Autowired
    public CollectionStoryRepositoryImpl(CollectionStoryMapper collectionStoryMapper) {
        this.collectionStoryMapper = collectionStoryMapper;
    }

    /**
     * 根据收藏品ID获取收藏故事列表
     *
     * @param collectionId 收藏品ID
     * @return 收藏故事列表
     */
    @Override
    public List<CollectionStoryEntity> getListByCollectionId(String collectionId) {
        return collectionStoryMapper.selectList(new QueryWrapper<CollectionStoryEntity>()
                .eq("collection_id", collectionId)
                .orderByDesc("order_no"));
    }

    /**
     * 根据收藏品ID删除收藏故事
     *
     * @param collectionId 收藏品ID
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteByCollectionId(String collectionId) {
        if (CharSequenceUtil.isBlank(collectionId)) {
            return false;
        }
        return collectionStoryMapper.delete(new QueryWrapper<CollectionStoryEntity>()
                .eq("collection_id", collectionId)) > 0;
    }
}




