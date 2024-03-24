package org.sziit.infrastructure.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.sziit.infrastructure.dao.domain.AccountRoleEntity;
import org.sziit.infrastructure.dao.mapper.AccountRoleMapper;
import org.sziit.infrastructure.repository.AccountRoleRepository;

/**
 * @author poboking
 * @description 针对表【account_role】的数据库操作Service实现
 * @createDate 2024-03-07 17:31:43
 */
@Service
public class AccountRoleRepositoryImpl extends ServiceImpl<AccountRoleMapper, AccountRoleEntity>
        implements AccountRoleRepository {

}




