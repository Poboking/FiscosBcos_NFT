package org.sziit.app.biz.convert.artwork;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.sziit.app.biz.artwork.dto.creator.CreatorRespDTO;
import org.sziit.infrastructure.dao.domain.CreatorEntity;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/10 20:32
 */
@Mapper
public interface CreatorConvert {
    CreatorConvert INSTANCE = Mappers.getMapper(CreatorConvert.class);

    CreatorRespDTO convertToRespDTO(CreatorEntity entity);
}
