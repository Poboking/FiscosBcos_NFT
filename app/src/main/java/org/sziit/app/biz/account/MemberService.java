package org.sziit.app.biz.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sziit.app.biz.account.dto.MemberReqDto;
import org.sziit.app.biz.convert.account.MemberConvert;
import org.sziit.app.biz.vo.account.AccountVo;
import org.sziit.infrastructure.repository.impl.MemberRepositoryImpl;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/8 21:39
 */
@Service
public class MemberService {
    @Autowired
    private MemberRepositoryImpl memberRepository;

    public AccountVo getAccountInfo(String memberId) {
        return MemberConvert.INSTANCE.convert(memberRepository.getAccountInfo(memberId));
    }

    public boolean updateNickName(MemberReqDto reqDto) {
        return memberRepository.updateNickName(reqDto.getId(), reqDto.getNickName());
    }
}
