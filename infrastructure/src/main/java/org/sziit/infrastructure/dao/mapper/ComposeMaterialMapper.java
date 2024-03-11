package org.sziit.infrastructure.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.sziit.infrastructure.dao.domain.ComposeMaterialEntity;

/**
 * @author poboking
 * @description 针对表【compose_material】的数据库操作Mapper
 * @createDate 2024-03-07 17:31:43
 * @Entity domain.ComposeMaterialEntity
 */

@Mapper
public interface ComposeMaterialMapper extends BaseMapper<ComposeMaterialEntity> {

}




