package org.sziit.infrastructure.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.sziit.infrastructure.dao.domain.SettlementAccountEntity;
import org.sziit.infrastructure.dao.mapper.SettlementAccountMapper;
import org.sziit.infrastructure.repository.SettlementAccountRepository;

/**
 * @author poboking
 * @description 针对表【settlement_account】的数据库操作Service实现
 * @createDate 2024-03-07 17:31:43
 */
@Service
public class SettlementAccountRepositoryImpl extends ServiceImpl<SettlementAccountMapper, SettlementAccountEntity>
        implements SettlementAccountRepository {

}




