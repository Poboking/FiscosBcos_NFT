package org.sziit.infrastructure.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.sziit.infrastructure.dao.domain.SystemSettingEntity;

/**
 * @author poboking
 * @description 针对表【system_setting】的数据库操作Mapper
 * @createDate 2024-03-07 17:31:43
 * @Entity domain.SystemSettingEntity
 */
@Mapper
public interface SystemSettingMapper extends BaseMapper<SystemSettingEntity> {

}




