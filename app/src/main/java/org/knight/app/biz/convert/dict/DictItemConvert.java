package org.knight.app.biz.convert.dict;

import org.knight.app.biz.dict.dto.DictItemRespDTo;
import org.knight.infrastructure.dao.domain.DictItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/10 19:02
 */
@Mapper
@SuppressWarnings("UnmappedTargetProperties")
public interface DictItemConvert {
    DictItemConvert INSTANCE = Mappers.getMapper(DictItemConvert.class);

    DictItemRespDTo convert(DictItemEntity bean);
}
