package org.sziit.infrastructure.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.sziit.infrastructure.dao.domain.RoleEntity;
import org.sziit.infrastructure.dao.mapper.RoleMapper;
import org.sziit.infrastructure.repository.RoleRepository;

/**
 * @author poboking
 * @description 针对表【role】的数据库操作Service实现
 * @createDate 2024-03-07 17:31:42
 */
@Service
public class RoleRepositoryImpl extends ServiceImpl<RoleMapper, RoleEntity>
        implements RoleRepository {

}




