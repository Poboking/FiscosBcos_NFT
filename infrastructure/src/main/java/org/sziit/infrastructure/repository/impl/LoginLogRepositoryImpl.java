package org.sziit.infrastructure.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.sziit.infrastructure.dao.domain.LoginLogEntity;
import org.sziit.infrastructure.dao.mapper.LoginLogMapper;
import org.sziit.infrastructure.repository.LoginLogRepository;

/**
 * @author poboking
 * @description 针对表【login_log】的数据库操作Service实现
 * @createDate 2024-03-07 17:31:42
 */
@Service
public class LoginLogRepositoryImpl extends ServiceImpl<LoginLogMapper, LoginLogEntity>
        implements LoginLogRepository {

}




