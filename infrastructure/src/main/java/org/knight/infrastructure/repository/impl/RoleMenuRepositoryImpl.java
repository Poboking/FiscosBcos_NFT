package org.knight.infrastructure.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.knight.infrastructure.dao.domain.RoleMenuEntity;
import org.knight.infrastructure.dao.mapper.RoleMenuMapper;
import org.knight.infrastructure.repository.RoleMenuRepository;

/**
 * @author poboking
 * @description 针对表【role_menu】的数据库操作Service实现
 * @createDate 2024-03-07 17:31:43
 */
@Service
public class RoleMenuRepositoryImpl extends ServiceImpl<RoleMenuMapper, RoleMenuEntity>
        implements RoleMenuRepository {

}




