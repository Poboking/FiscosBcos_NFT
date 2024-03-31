package org.knight.app.biz.convert.transaction;

import org.knight.app.biz.transaction.dto.giverecord.CollectionGiveRecordRespDTO;
import org.knight.infrastructure.dao.domain.CollectionGiveRecordEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/18 15:26
 */
@Mapper
public interface CollectionGiveRecordConvert {

    CollectionGiveRecordConvert INSTANCE = Mappers.getMapper(CollectionGiveRecordConvert.class);

    CollectionGiveRecordRespDTO convertToDTO(CollectionGiveRecordEntity collectionGiveRecordEntity);

    List<CollectionGiveRecordRespDTO> convertToDTO(List<CollectionGiveRecordEntity> collectionGiveRecordEntity);
}
