package org.knight.infrastructure.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.knight.infrastructure.dao.domain.WithdrawRecordEntity;
import org.knight.infrastructure.dao.mapper.WithdrawRecordMapper;
import org.knight.infrastructure.repository.WithdrawRecordRepository;

/**
 * @author poboking
 * @description 针对表【withdraw_record】的数据库操作Service实现
 * @createDate 2024-03-07 17:31:43
 */
@Service
public class WithdrawRecordRepositoryImpl extends ServiceImpl<WithdrawRecordMapper, WithdrawRecordEntity>
        implements WithdrawRecordRepository {

}




