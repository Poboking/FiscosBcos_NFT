package org.knight.infrastructure.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.knight.infrastructure.dao.domain.ExchangeCodeEntity;
import org.knight.infrastructure.dao.mapper.ExchangeCodeMapper;
import org.knight.infrastructure.repository.ExchangeCodeRepository;

/**
 * @author poboking
 * @description 针对表【exchange_code】的数据库操作Service实现
 * @createDate 2024-03-07 17:31:42
 */
@Service
public class ExchangeCodeRepositoryImpl extends ServiceImpl<ExchangeCodeMapper, ExchangeCodeEntity>
        implements ExchangeCodeRepository {

}




