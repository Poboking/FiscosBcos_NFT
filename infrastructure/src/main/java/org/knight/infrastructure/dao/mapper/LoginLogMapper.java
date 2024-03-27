package org.knight.infrastructure.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.knight.infrastructure.dao.domain.LoginLogEntity;

/**
 * @author poboking
 * @description 针对表【login_log】的数据库操作Mapper
 * @createDate 2024-03-07 17:31:42
 * @Entity domain.LoginLogEntity
 */
@Mapper
public interface LoginLogMapper extends BaseMapper<LoginLogEntity> {

}




