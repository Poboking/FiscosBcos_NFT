package org.knight.infrastructure.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.knight.infrastructure.dao.domain.OperLogEntity;

/**
 * @author poboking
 * @description 针对表【oper_log】的数据库操作Mapper
 * @createDate 2024-03-07 17:31:43
 * @Entity domain.OperLogEntity
 */
@Mapper
public interface OperLogMapper extends BaseMapper<OperLogEntity> {

}




