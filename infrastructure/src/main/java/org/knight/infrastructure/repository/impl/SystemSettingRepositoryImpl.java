package org.knight.infrastructure.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.knight.infrastructure.dao.domain.SystemSettingEntity;
import org.knight.infrastructure.dao.mapper.SystemSettingMapper;
import org.knight.infrastructure.repository.SystemSettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author poboking
 * @description 针对表【system_setting】的数据库操作Service实现
 * @createDate 2024-03-07 17:31:43
 */
@Service
public class SystemSettingRepositoryImpl extends ServiceImpl<SystemSettingMapper, SystemSettingEntity>
        implements SystemSettingRepository {

    @Autowired
    private SystemSettingMapper systemSettingMapper;

    /**
     * 获取最新的系统设置项
     *
     * @return 最新的系统设置项
     */
    @Override
    public SystemSettingEntity getLatestByLatestUpdateTime() {
        return systemSettingMapper.selectOne(new QueryWrapper<SystemSettingEntity>()
                .orderByDesc("lately_update_time")
                .last("LIMIT 1"));
    }
}




