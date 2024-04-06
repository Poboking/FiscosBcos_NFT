package org.knight.app.biz.convert.log;

import org.knight.app.biz.log.dto.collectionlog.IssuedCollectionActionLogRespDTO;
import org.knight.infrastructure.dao.domain.IssuedCollectionActionLogEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/6 16:59
 */
@Mapper
public interface IssuedCollectionActionLogConvert {
    IssuedCollectionActionLogConvert INSTANCE = Mappers.getMapper(IssuedCollectionActionLogConvert.class);

    @Mapping(target = "actionTime", ignore = true)
    IssuedCollectionActionLogRespDTO convertToRespDTO(IssuedCollectionActionLogEntity bean);

    List<IssuedCollectionActionLogRespDTO> convertToRespDTO(List<IssuedCollectionActionLogEntity> bean);

}
