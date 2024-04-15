package org.knight.app.biz.convert.artwork;

import org.knight.app.biz.artwork.dto.holdcollection.MemberHoldCollectionDetailRespDTO;
import org.knight.app.biz.artwork.dto.holdcollection.MemberHoldCollectionRespDTO;
import org.knight.app.biz.artwork.dto.holdcollection.MyHoldCollectionDetailRespDTO;
import org.knight.infrastructure.dao.domain.MemberHoldCollectionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/5 20:17
 */
@Mapper
public interface MemberHoldCollectionConvert {
    MemberHoldCollectionConvert INSTANCE = Mappers.getMapper(MemberHoldCollectionConvert.class);

    @Mapping(target = "holdTime", ignore = true)
    @Mapping(target = "loseTime", ignore = true)
    MemberHoldCollectionRespDTO convertToRespDTO(MemberHoldCollectionEntity bean);

    List<MemberHoldCollectionRespDTO> convertToRespDTO(List<MemberHoldCollectionEntity> bean);


    MyHoldCollectionDetailRespDTO convertToDetailRespDTO(MemberHoldCollectionDetailRespDTO bean);

    List<MyHoldCollectionDetailRespDTO> convertToDetailRespDTO(List<MemberHoldCollectionDetailRespDTO> bean);
}
