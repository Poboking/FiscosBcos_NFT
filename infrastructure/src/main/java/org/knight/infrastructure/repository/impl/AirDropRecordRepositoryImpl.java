package org.knight.infrastructure.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.knight.infrastructure.dao.domain.AirDropRecordEntity;
import org.knight.infrastructure.dao.mapper.AirDropRecordMapper;
import org.knight.infrastructure.repository.AirDropRecordRepository;

/**
 * @author poboking
 * @description 针对表【air_drop_record】的数据库操作Service实现
 * @createDate 2024-03-07 17:31:43
 */
@Service
public class AirDropRecordRepositoryImpl extends ServiceImpl<AirDropRecordMapper, AirDropRecordEntity>
        implements AirDropRecordRepository {

}




