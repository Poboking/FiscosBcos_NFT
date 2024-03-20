package org.sziit.app.biz.convert.log;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.sziit.app.biz.log.dto.loginlog.LoginLogReqDTO;
import org.sziit.app.biz.log.dto.loginlog.LoginLogRespDTO;
import org.sziit.infrastructure.dao.domain.LoginLogEntity;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/13 14:51
 */
@Mapper
@SuppressWarnings("UnmappedTargetProperties")
public interface LoginLogConvert {
    LoginLogConvert INSTANCE = Mappers.getMapper(LoginLogConvert.class);

    LoginLogEntity convertToEntity(LoginLogReqDTO repDto);

    LoginLogEntity convertToEntity(LoginLogRespDTO repDto);

    LoginLogRespDTO convertToRespDto(LoginLogEntity entity);

    LoginLogReqDTO convertToReqDto(LoginLogEntity entity);
}
