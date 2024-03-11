package org.sziit.infrastructure.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.sziit.infrastructure.dao.domain.AirDropRecordEntity;
import org.sziit.infrastructure.dao.mapper.AirDropRecordMapper;
import org.sziit.infrastructure.repository.AirDropRecordService;

/**
 * @author poboking
 * @description 针对表【air_drop_record】的数据库操作Service实现
 * @createDate 2024-03-07 17:31:43
 */
@Service
public class AirDropRecordServiceImpl extends ServiceImpl<AirDropRecordMapper, AirDropRecordEntity>
        implements AirDropRecordService {

}




