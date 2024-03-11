package org.sziit.infrastructure.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.sziit.infrastructure.dao.domain.ComposeRecordEntity;
import org.sziit.infrastructure.dao.mapper.ComposeRecordMapper;
import org.sziit.infrastructure.repository.ComposeRecordService;

/**
 * @author poboking
 * @description 针对表【compose_record】的数据库操作Service实现
 * @createDate 2024-03-07 17:31:43
 */
@Service
public class ComposeRecordServiceImpl extends ServiceImpl<ComposeRecordMapper, ComposeRecordEntity>
        implements ComposeRecordService {

}




