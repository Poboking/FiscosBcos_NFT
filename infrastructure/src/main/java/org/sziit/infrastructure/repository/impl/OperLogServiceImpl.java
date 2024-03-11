package org.sziit.infrastructure.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.sziit.infrastructure.dao.domain.OperLogEntity;
import org.sziit.infrastructure.dao.mapper.OperLogMapper;
import org.sziit.infrastructure.repository.OperLogService;

/**
 * @author poboking
 * @description 针对表【oper_log】的数据库操作Service实现
 * @createDate 2024-03-07 17:31:43
 */
@Service
public class OperLogServiceImpl extends ServiceImpl<OperLogMapper, OperLogEntity>
        implements OperLogService {

}




