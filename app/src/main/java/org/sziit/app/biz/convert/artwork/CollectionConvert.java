package org.sziit.app.biz.convert.artwork;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.sziit.app.biz.artwork.dto.collection.CollectionDetailRespDto;
import org.sziit.infrastructure.dao.domain.CollectionEntity;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/8 22:18
 */
@Mapper
@SuppressWarnings("UnmappedTargetProperties")
public interface CollectionConvert {
    CollectionConvert INSTANCE = Mappers.getMapper(CollectionConvert.class);

    CollectionDetailRespDto convert(CollectionEntity bean);
}
