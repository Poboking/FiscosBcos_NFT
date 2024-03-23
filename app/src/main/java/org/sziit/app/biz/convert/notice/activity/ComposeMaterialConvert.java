package org.sziit.app.biz.convert.notice.activity;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.sziit.app.biz.notice.dto.activity.ComposeMaterialRespDTO;
import org.sziit.infrastructure.dao.domain.ComposeMaterialEntity;

import java.util.List;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/19 21:50
 */
@Mapper
public interface ComposeMaterialConvert {

    ComposeMaterialConvert INSTANCE = Mappers.getMapper(ComposeMaterialConvert.class);

    ComposeMaterialRespDTO convertToRespDTO(ComposeMaterialEntity bean);

    List<ComposeMaterialRespDTO> convertToRespDTO(List<ComposeMaterialEntity> beans);
}
