package org.knight.infrastructure.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.knight.infrastructure.dao.domain.CollectionEntity;

/**
 * @author poboking
 * @description 针对表【collection】的数据库操作Mapper
 * @createDate 2024-03-07 17:31:43
 * @Entity domain.CollectionEntity
 */

@Mapper
public interface CollectionMapper extends BaseMapper<CollectionEntity> {

    IPage<CollectionEntity> getPageByTypeOrName(IPage<CollectionEntity> page, String commodityType, String name);

    IPage<CollectionEntity> getPageByType(IPage<CollectionEntity> page, String commodityType);

    IPage<CollectionEntity> getPageByName(IPage<CollectionEntity> page, String name);

    IPage<CollectionEntity> getPage(IPage<CollectionEntity> page);
}




