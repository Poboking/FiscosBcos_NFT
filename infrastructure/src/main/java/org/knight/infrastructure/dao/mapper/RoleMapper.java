package org.knight.infrastructure.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.knight.infrastructure.dao.domain.RoleEntity;

/**
 * @author poboking
 * @description 针对表【role】的数据库操作Mapper
 * @createDate 2024-03-07 17:31:42
 * @Entity domain.RoleEntity
 */
@Mapper
public interface RoleMapper extends BaseMapper<RoleEntity> {

}




