package org.sziit.infrastructure.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.sziit.infrastructure.dao.domain.SystemSettingEntity;
import org.sziit.infrastructure.dao.mapper.SystemSettingMapper;
import org.sziit.infrastructure.repository.SystemSettingService;

/**
 * @author poboking
 * @description 针对表【system_setting】的数据库操作Service实现
 * @createDate 2024-03-07 17:31:43
 */
@Service
public class SystemSettingServiceImpl extends ServiceImpl<SystemSettingMapper, SystemSettingEntity>
        implements SystemSettingService {

}




