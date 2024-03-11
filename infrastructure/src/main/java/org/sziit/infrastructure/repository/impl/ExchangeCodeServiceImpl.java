package org.sziit.infrastructure.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.sziit.infrastructure.dao.domain.ExchangeCodeEntity;
import org.sziit.infrastructure.dao.mapper.ExchangeCodeMapper;
import org.sziit.infrastructure.repository.ExchangeCodeService;

/**
 * @author poboking
 * @description 针对表【exchange_code】的数据库操作Service实现
 * @createDate 2024-03-07 17:31:42
 */
@Service
public class ExchangeCodeServiceImpl extends ServiceImpl<ExchangeCodeMapper, ExchangeCodeEntity>
        implements ExchangeCodeService {

}




