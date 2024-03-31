package org.knight.app.biz.convert.transaction;

import org.knight.app.biz.transaction.dto.settlement.SettlementAccountAddReqDTO;
import org.knight.app.biz.transaction.dto.settlement.SettlementAccountRespDTO;
import org.knight.app.biz.transaction.dto.settlement.SettlementAccountUpdateReqDTO;
import org.knight.infrastructure.dao.domain.SettlementAccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/23 18:03
 */
@Mapper
public interface SettlementAccountConvert {

    SettlementAccountConvert INSTANCE = Mappers.getMapper(SettlementAccountConvert.class);

    SettlementAccountRespDTO convertToRespDTO(SettlementAccountEntity bean);


    List<SettlementAccountRespDTO> convertToRespDTO(List<SettlementAccountEntity> beans);

    SettlementAccountEntity convertToEntity(SettlementAccountUpdateReqDTO dto);

    SettlementAccountEntity convertToEntity(SettlementAccountAddReqDTO dto);
}
