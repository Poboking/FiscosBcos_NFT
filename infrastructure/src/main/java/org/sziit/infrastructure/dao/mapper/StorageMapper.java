package org.sziit.infrastructure.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.sziit.infrastructure.dao.domain.StorageEntity;

/**
 * @author poboking
 * @description 针对表【storage】的数据库操作Mapper
 * @createDate 2024-03-07 17:31:42
 * @Entity domain.StorageEntity
 */
@Mapper
public interface StorageMapper extends BaseMapper<StorageEntity> {

}




