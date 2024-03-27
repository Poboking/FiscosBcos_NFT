package org.knight.infrastructure.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.knight.infrastructure.dao.domain.CollectionStoryEntity;
import org.knight.infrastructure.dao.mapper.CollectionStoryMapper;
import org.knight.infrastructure.repository.CollectionStoryRepository;

/**
 * @author poboking
 * @description 针对表【collection_story】的数据库操作Service实现
 * @createDate 2024-03-07 17:31:43
 */
@Service
public class CollectionStoryRepositoryImpl extends ServiceImpl<CollectionStoryMapper, CollectionStoryEntity>
        implements CollectionStoryRepository {

}



