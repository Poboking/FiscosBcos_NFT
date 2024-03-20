package org.sziit.app.biz.convert.artwork;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.sziit.app.biz.artwork.dto.mysteryBox.MysteryBoxRespDTO;
import org.sziit.infrastructure.dao.domain.CollectionEntity;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/19 20:53
 */
@Mapper
public interface MysteryBoxConvert {
    MysteryBoxConvert  INSTANCE = Mappers.getMapper(MysteryBoxConvert.class);

    MysteryBoxRespDTO convertToRespDTO(CollectionEntity bean);
}
