package org.knight.infrastructure.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.knight.infrastructure.dao.domain.CreatorEntity;
import org.knight.infrastructure.dao.mapper.CreatorMapper;
import org.knight.infrastructure.repository.CreatorRepository;

/**
 * @author poboking
 * @description 针对表【creator】的数据库操作Service实现
 * @createDate 2024-03-07 17:31:43
 */
@Service
public class CreatorRepositoryImpl extends ServiceImpl<CreatorMapper, CreatorEntity>
        implements CreatorRepository {

}




