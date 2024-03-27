package org.knight.app.biz.convert.artwork;

import org.knight.app.biz.artwork.dto.collection.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.knight.app.biz.artwork.dto.collection.*;
import org.knight.infrastructure.dao.domain.CollectionEntity;
import org.knight.infrastructure.dao.domain.MemberResaleCollectionEntity;

import java.util.List;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/8 22:18
 */
@Mapper
@SuppressWarnings("UnmappedTargetProperties")
public interface CollectionConvert {
    CollectionConvert INSTANCE = Mappers.getMapper(CollectionConvert.class);

    CollectionDetailRespDTO convertToDetailRespDTO(CollectionEntity bean);

    CollectionIntroRespDTO convertToIntroRespDTO(CollectionEntity bean);

    CollectionResaleRespDTO convertToResaleRespDTO(MemberResaleCollectionEntity bean);

    ForSaleCollectionRespDTO convertToForSaleRespDTO(CollectionEntity bean);

    List<ForSaleCollectionRespDTO> convertToForSaleRespDTO(List<CollectionEntity> bean);

    CollectionRespDTO convertToRespDTO(CollectionEntity bean);

    List<CollectionRespDTO> convertToRespDTO(List<CollectionEntity> bean);
}
