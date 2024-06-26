package org.knight.infrastructure.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.knight.infrastructure.dao.domain.ChainSettingEntity;
import org.knight.infrastructure.dao.mapper.ChainSettingMapper;
import org.knight.infrastructure.repository.ChainSettingRepository;
import org.springframework.stereotype.Service;

/**
 * @author poboking
 * @description 针对表【chain_setting】的数据库操作Service实现
 * @createDate 2024-03-07 17:31:43
 */
@Service
public class ChainSettingRepositoryImpl extends ServiceImpl<ChainSettingMapper, ChainSettingEntity>
        implements ChainSettingRepository {

}




