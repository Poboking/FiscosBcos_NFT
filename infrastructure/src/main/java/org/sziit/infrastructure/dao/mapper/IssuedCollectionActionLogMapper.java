package org.sziit.infrastructure.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.sziit.infrastructure.dao.domain.IssuedCollectionActionLogEntity;

/**
 * @author poboking
 * @description 针对表【issued_collection_action_log】的数据库操作Mapper
 * @createDate 2024-03-07 17:31:42
 * @Entity domain.IssuedCollectionActionLogEntity
 */

@Mapper
public interface IssuedCollectionActionLogMapper extends BaseMapper<IssuedCollectionActionLogEntity> {

}




