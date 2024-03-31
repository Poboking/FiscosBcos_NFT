package org.knight.app.biz.convert.artwork;

import org.knight.app.biz.artwork.dto.creator.CreatorAddOrUpdateReqDTO;
import org.knight.app.biz.artwork.dto.creator.CreatorRespDTO;
import org.knight.infrastructure.dao.domain.CreatorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/10 20:32
 */
@Mapper
public interface CreatorConvert {
    CreatorConvert INSTANCE = Mappers.getMapper(CreatorConvert.class);

    CreatorRespDTO convertToRespDTO(CreatorEntity entity);

    List<CreatorRespDTO> convertToRespDTO(List<CreatorEntity> entity);

    CreatorEntity convertToEntity(CreatorAddOrUpdateReqDTO reqDTo);
}
