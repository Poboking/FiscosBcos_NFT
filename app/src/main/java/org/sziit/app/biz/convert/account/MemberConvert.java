package org.sziit.app.biz.convert.account;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.sziit.app.biz.account.dto.account.AccountRespDTO;
import org.sziit.infrastructure.dao.domain.MemberEntity;

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
