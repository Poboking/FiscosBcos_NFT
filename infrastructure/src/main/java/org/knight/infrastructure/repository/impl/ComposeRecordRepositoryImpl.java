package org.knight.infrastructure.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.knight.infrastructure.dao.domain.ComposeRecordEntity;
import org.knight.infrastructure.dao.mapper.ComposeRecordMapper;
import org.knight.infrastructure.repository.ComposeRecordRepository;

/**
 * @author poboking
 * @description 针对表【compose_record】的数据库操作Service实现
 * @createDate 2024-03-07 17:31:43
 */
@Service
public class ComposeRecordRepositoryImpl extends ServiceImpl<ComposeRecordMapper, ComposeRecordEntity>
        implements ComposeRecordRepository {

}




