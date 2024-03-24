package org.sziit.app.biz.convert.account;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.sziit.app.biz.account.dto.account.background.BackgroundAccountRespDTO;
import org.sziit.infrastructure.dao.domain.BackgroundAccountEntity;

import java.util.List;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/24 16:59
 */

@Mapper
public interface BackgroundAccountConvert {

    BackgroundAccountConvert INSTANCE = Mappers.getMapper(BackgroundAccountConvert.class);

    BackgroundAccountRespDTO convertToRespDTO(BackgroundAccountEntity entity);

    List<BackgroundAccountRespDTO> convertToRespDTO(List<BackgroundAccountEntity> entity);

}
