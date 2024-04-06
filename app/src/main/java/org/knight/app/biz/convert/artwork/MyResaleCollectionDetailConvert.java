package org.knight.app.biz.convert.artwork;

import org.knight.app.biz.artwork.dto.collection.CollectionResaleDetailRespDTO;
import org.knight.app.biz.artwork.dto.holdcollection.MyResaleCollectionDetailRespDTO;
import org.knight.infrastructure.dao.domain.MemberResaleCollectionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/6 14:58
 */
@Mapper
public interface MyResaleCollectionDetailConvert {

    MyResaleCollectionDetailConvert INSTANCE = Mappers.getMapper(MyResaleCollectionDetailConvert.class);

    MyResaleCollectionDetailRespDTO convertToRespDTOByResp(CollectionResaleDetailRespDTO bean);

    MyResaleCollectionDetailRespDTO convertToRespDTO(MemberResaleCollectionEntity bean);

    List<MyResaleCollectionDetailRespDTO> convertToRespDTOs(List<MemberResaleCollectionEntity> bean);

    List<MyResaleCollectionDetailRespDTO> convertToRespDTOsByResp(List<CollectionResaleDetailRespDTO> bean);

}
