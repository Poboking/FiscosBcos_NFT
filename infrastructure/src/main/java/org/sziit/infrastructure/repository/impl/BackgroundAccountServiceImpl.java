package org.sziit.infrastructure.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.sziit.infrastructure.dao.domain.BackgroundAccountEntity;
import org.sziit.infrastructure.dao.mapper.BackgroundAccountMapper;
import org.sziit.infrastructure.repository.BackgroundAccountService;

/**
 * @author poboking
 * @description 针对表【background_account】的数据库操作Service实现
 * @createDate 2024-03-07 17:31:43
 */
@Service
public class BackgroundAccountServiceImpl extends ServiceImpl<BackgroundAccountMapper, BackgroundAccountEntity>
        implements BackgroundAccountService {

}




