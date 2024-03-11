package org.sziit.infrastructure.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.sziit.infrastructure.dao.domain.StorageEntity;
import org.sziit.infrastructure.dao.mapper.StorageMapper;
import org.sziit.infrastructure.repository.StorageService;

/**
 * @author poboking
 * @description 针对表【storage】的数据库操作Service实现
 * @createDate 2024-03-07 17:31:43
 */
@Service
public class StorageServiceImpl extends ServiceImpl<StorageMapper, StorageEntity>
        implements StorageService {

}




