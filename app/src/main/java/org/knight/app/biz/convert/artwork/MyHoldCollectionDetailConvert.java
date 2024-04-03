package org.knight.app.biz.convert.artwork;

import org.knight.app.biz.artwork.dto.collection.CollectionResaleDetailRespDTO;
import org.knight.app.biz.artwork.dto.holdcollection.MyHoldCollectionDetailRespDTO;
import org.knight.infrastructure.dao.domain.MemberResaleCollectionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/1 17:42
 */
@Mapper
public interface MyHoldCollectionDetailConvert {
    MyHoldCollectionDetailConvert INSTANCE = Mappers.getMapper(MyHoldCollectionDetailConvert.class);

    MyHoldCollectionDetailRespDTO convertToRespDTO(MemberResaleCollectionEntity entity);
    MyHoldCollectionDetailRespDTO convertToRespDTOByResp(CollectionResaleDetailRespDTO entity);

    List<MyHoldCollectionDetailRespDTO> convertToRespDTO(List<MemberResaleCollectionEntity> entity);
    List<MyHoldCollectionDetailRespDTO> convertToRespDTOByResp(List<CollectionResaleDetailRespDTO> entity);

}
