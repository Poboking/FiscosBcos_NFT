package org.knight.app.biz.convert.artwork;

import org.knight.app.biz.artwork.dto.holdcollection.MyResaleCollectionRespDTO;
import org.knight.app.biz.artwork.dto.holdcollection.MySaleCollectionRespDTO;
import org.knight.infrastructure.dao.domain.MemberResaleCollectionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/1 17:22
 */
@Mapper
public interface MyResaleCollectionConvert {

    MyResaleCollectionConvert INSTANCE = Mappers.getMapper(MyResaleCollectionConvert.class);

    MyResaleCollectionRespDTO convertToRespDTO(MemberResaleCollectionEntity entity);

    List<MyResaleCollectionRespDTO> convertToRespDTO(List<MemberResaleCollectionEntity> entity);
}
