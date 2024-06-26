package org.knight.infrastructure.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.knight.infrastructure.dao.domain.StorageEntity;
import org.knight.infrastructure.dao.mapper.StorageMapper;
import org.knight.infrastructure.repository.StorageRepository;
import org.springframework.stereotype.Service;

/**
 * @author poboking
 * @description 针对表【storage】的数据库操作Service实现
 * @createDate 2024-03-07 17:31:43
 */
@Service
public class StorageRepositoryImpl extends ServiceImpl<StorageMapper, StorageEntity>
        implements StorageRepository {

}




