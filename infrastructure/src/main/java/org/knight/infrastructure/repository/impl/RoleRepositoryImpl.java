package org.knight.infrastructure.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.knight.infrastructure.dao.domain.RoleEntity;
import org.knight.infrastructure.dao.mapper.RoleMapper;
import org.knight.infrastructure.repository.RoleRepository;
import org.springframework.stereotype.Service;

/**
 * @author poboking
 * @description 针对表【role】的数据库操作Service实现
 * @createDate 2024-03-07 17:31:42
 */
@Service
public class RoleRepositoryImpl extends ServiceImpl<RoleMapper, RoleEntity>
        implements RoleRepository {

}




