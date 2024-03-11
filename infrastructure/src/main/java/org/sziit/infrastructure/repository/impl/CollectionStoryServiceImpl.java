package org.sziit.infrastructure.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.sziit.infrastructure.dao.domain.CollectionStoryEntity;
import org.sziit.infrastructure.dao.mapper.CollectionStoryMapper;
import org.sziit.infrastructure.repository.CollectionStoryService;

/**
 * @author poboking
 * @description 针对表【collection_story】的数据库操作Service实现
 * @createDate 2024-03-07 17:31:43
 */
@Service
public class CollectionStoryServiceImpl extends ServiceImpl<CollectionStoryMapper, CollectionStoryEntity>
        implements CollectionStoryService {

}




