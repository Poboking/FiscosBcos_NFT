package org.sziit.app.biz.convert.transaction;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.sziit.app.biz.transaction.dto.order.PayOrderRespDTO;
import org.sziit.infrastructure.dao.domain.PayOrderEntity;

import java.util.List;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/18 0:02
 */
@Mapper
public interface PayOrderConvert {

    PayOrderConvert INSTANCE = Mappers.getMapper(PayOrderConvert.class);

    PayOrderRespDTO convertToDTO(PayOrderEntity payOrderRespDTO);

    List<PayOrderRespDTO> convertToDTO(List<PayOrderEntity> payOrderRespDTO);
}
