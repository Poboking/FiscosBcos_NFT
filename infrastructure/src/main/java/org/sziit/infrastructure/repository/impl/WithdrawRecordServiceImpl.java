package org.sziit.infrastructure.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.sziit.infrastructure.dao.domain.WithdrawRecordEntity;
import org.sziit.infrastructure.dao.mapper.WithdrawRecordMapper;
import org.sziit.infrastructure.repository.WithdrawRecordService;

/**
 * @author poboking
 * @description 针对表【withdraw_record】的数据库操作Service实现
 * @createDate 2024-03-07 17:31:43
 */
@Service
public class WithdrawRecordServiceImpl extends ServiceImpl<WithdrawRecordMapper, WithdrawRecordEntity>
        implements WithdrawRecordService {

}




