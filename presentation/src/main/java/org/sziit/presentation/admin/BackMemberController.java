package org.sziit.presentation.admin;

import com.feiniaojin.gracefulresponse.api.ValidationStatusCode;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sziit.app.biz.account.MemberService;
import org.sziit.app.biz.account.dto.member.MemberStatisticDataRespDTO;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/24 15:10
 */
@Log4j2
@RestController
@RequestMapping("/back/member/")
public class BackMemberController {

    private final MemberService memberService;

    @Autowired
    public BackMemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    @GetMapping("getMemberStatisticData")
    @ValidationStatusCode(code = "500")
    public MemberStatisticDataRespDTO getMemberStatisticData() {
        return memberService.getMemberStatisticData();
    }


}
