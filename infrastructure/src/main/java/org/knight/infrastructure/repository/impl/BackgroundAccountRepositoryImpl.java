package org.knight.infrastructure.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.knight.infrastructure.dao.domain.BackgroundAccountEntity;
import org.knight.infrastructure.dao.mapper.BackgroundAccountMapper;
import org.knight.infrastructure.repository.BackgroundAccountRepository;
import org.springframework.stereotype.Service;

/**
 * @author poboking
 * @description 针对表【background_account】的数据库操作Service实现
 * @createDate 2024-03-07 17:31:43
 */
@Service
public class BackgroundAccountRepositoryImpl extends ServiceImpl<BackgroundAccountMapper, BackgroundAccountEntity>
        implements BackgroundAccountRepository {

}




