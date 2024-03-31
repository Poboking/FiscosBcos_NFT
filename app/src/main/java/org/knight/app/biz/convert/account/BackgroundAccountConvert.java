package org.knight.app.biz.convert.account;

import org.knight.app.biz.account.dto.account.background.BackgroundAccountRespDTO;
import org.knight.infrastructure.dao.domain.BackgroundAccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

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
