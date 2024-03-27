package org.knight.app.biz.convert.account;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.knight.app.biz.account.dto.account.member.AccountRespDTO;
import org.knight.infrastructure.dao.domain.MemberEntity;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/8 22:17
 */
@Mapper
@SuppressWarnings("UnmappedTargetProperties")
public interface MemberConvert {
    MemberConvert INSTANCE = Mappers.getMapper(MemberConvert.class);

    AccountRespDTO convert(MemberEntity bean);

}
