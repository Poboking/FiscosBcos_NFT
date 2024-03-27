package org.knight.app.biz.convert.transaction;

import org.knight.app.biz.transaction.dto.withdraw.WithdrawRespDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.knight.infrastructure.dao.domain.WithdrawRecordEntity;

import java.util.List;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/23 19:53
 */
@Mapper
public interface WithdrawConvert {
    WithdrawConvert  INSTANCE  = Mappers.getMapper(WithdrawConvert.class);

    WithdrawRespDTO convertToRespDTO(WithdrawRecordEntity entity);

    List<WithdrawRespDTO> convertToRespDTO(List<WithdrawRecordEntity> entityList);
}
