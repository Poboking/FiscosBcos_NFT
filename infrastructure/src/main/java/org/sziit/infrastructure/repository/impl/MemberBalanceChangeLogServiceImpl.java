package org.sziit.infrastructure.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.sziit.infrastructure.dao.domain.MemberBalanceChangeLogEntity;
import org.sziit.infrastructure.dao.mapper.MemberBalanceChangeLogMapper;
import org.sziit.infrastructure.repository.MemberBalanceChangeLogService;

/**
 * @author poboking
 * @description 针对表【member_balance_change_log】的数据库操作Service实现
 * @createDate 2024-03-07 17:31:42
 */
@Service
public class MemberBalanceChangeLogServiceImpl extends ServiceImpl<MemberBalanceChangeLogMapper, MemberBalanceChangeLogEntity>
        implements MemberBalanceChangeLogService {

}




