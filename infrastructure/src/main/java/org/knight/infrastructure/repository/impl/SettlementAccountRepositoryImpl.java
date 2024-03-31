package org.knight.infrastructure.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.knight.infrastructure.dao.domain.SettlementAccountEntity;
import org.knight.infrastructure.dao.mapper.SettlementAccountMapper;
import org.knight.infrastructure.repository.SettlementAccountRepository;
import org.springframework.stereotype.Service;

/**
 * @author poboking
 * @description 针对表【settlement_account】的数据库操作Service实现
 * @createDate 2024-03-07 17:31:43
 */
@Service
public class SettlementAccountRepositoryImpl extends ServiceImpl<SettlementAccountMapper, SettlementAccountEntity>
        implements SettlementAccountRepository {

}




