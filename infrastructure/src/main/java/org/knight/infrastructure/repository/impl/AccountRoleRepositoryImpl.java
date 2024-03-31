package org.knight.infrastructure.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.knight.infrastructure.dao.domain.AccountRoleEntity;
import org.knight.infrastructure.dao.mapper.AccountRoleMapper;
import org.knight.infrastructure.repository.AccountRoleRepository;
import org.springframework.stereotype.Service;

/**
 * @author poboking
 * @description 针对表【account_role】的数据库操作Service实现
 * @createDate 2024-03-07 17:31:43
 */
@Service
public class AccountRoleRepositoryImpl extends ServiceImpl<AccountRoleMapper, AccountRoleEntity>
        implements AccountRoleRepository {

}




