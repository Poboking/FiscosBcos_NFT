package org.knight.app.biz.convert.artwork;

import org.knight.app.biz.transaction.dto.issued.CastIssuedCollectionReqDTO;
import org.knight.infrastructure.dao.domain.IssuedCollectionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/8 12:36
 */
@Mapper
public interface IssuedCollectionConvert {
    IssuedCollectionConvert INSTANCE = Mappers.getMapper(IssuedCollectionConvert.class);

    IssuedCollectionEntity convertToEntity(CastIssuedCollectionReqDTO bean);
    List<IssuedCollectionEntity> convertToEntity(List<CastIssuedCollectionReqDTO> bean);
}
