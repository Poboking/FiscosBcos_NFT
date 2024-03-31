package org.knight.app.biz.convert.log;

import org.knight.app.biz.log.dto.balance.MemberBCLogRespDTO;
import org.knight.infrastructure.dao.domain.MemberBalanceChangeLogEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/17 23:09
 */
@Mapper
@SuppressWarnings("UnmappedTargetProperties")
public interface MemberBCLogConvert {
    MemberBCLogConvert INSTANCE = Mappers.getMapper(MemberBCLogConvert.class);

    MemberBCLogRespDTO convertToRespDto(MemberBalanceChangeLogEntity entity);

    List<MemberBCLogRespDTO> convertToRespDto(List<MemberBalanceChangeLogEntity> entity);
}
