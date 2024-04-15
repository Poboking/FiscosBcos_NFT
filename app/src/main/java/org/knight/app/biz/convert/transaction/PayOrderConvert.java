package org.knight.app.biz.convert.transaction;

import org.knight.app.biz.transaction.dto.order.MyPayOrderRespDTO;
import org.knight.app.biz.transaction.dto.order.PayOrderRespDTO;
import org.knight.infrastructure.dao.domain.PayOrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/18 0:02
 */
@Mapper
public interface PayOrderConvert {

    PayOrderConvert INSTANCE = Mappers.getMapper(PayOrderConvert.class);

    MyPayOrderRespDTO convertToMyRespDTO(PayOrderEntity bean);

    List<MyPayOrderRespDTO> convertToMyRespDTO(List<PayOrderEntity> bean);

    @Mapping(target = "paidTime", ignore = true)
    @Mapping(target = "cancelTime", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    PayOrderRespDTO convertToRespDTO(PayOrderEntity bean);

    List<PayOrderRespDTO> convertToRespDTO(List<PayOrderEntity> bean);
}
