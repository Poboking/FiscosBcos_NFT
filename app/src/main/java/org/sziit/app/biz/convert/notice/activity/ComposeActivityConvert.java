package org.sziit.app.biz.convert.notice.activity;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.sziit.app.biz.notice.dto.activity.ComposeActivityRespDTO;
import org.sziit.infrastructure.dao.domain.ComposeActivityEntity;

import java.util.List;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/17 20:19
 */
@Mapper
@SuppressWarnings("UnmappedTargetProperties")
public interface ComposeActivityConvert {
    ComposeActivityConvert INSTANCE = Mappers.getMapper(ComposeActivityConvert.class);

    ComposeActivityRespDTO convertToRespDTO(ComposeActivityEntity bean);

    List<ComposeActivityRespDTO> convertToRespDTO(List<ComposeActivityEntity> bean);
}
