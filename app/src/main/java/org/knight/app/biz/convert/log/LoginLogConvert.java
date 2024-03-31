package org.knight.app.biz.convert.log;

import org.knight.app.biz.log.dto.loginlog.LoginLogReqDTO;
import org.knight.app.biz.log.dto.loginlog.LoginLogRespDTO;
import org.knight.infrastructure.dao.domain.LoginLogEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

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
