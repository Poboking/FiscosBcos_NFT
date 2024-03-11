package org.sziit.presentation.member;

import com.feiniaojin.gracefulresponse.api.ValidationStatusCode;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.sziit.app.biz.account.MemberService;
import org.sziit.app.biz.account.dto.MemberReqDto;
import org.sziit.app.biz.vo.account.AccountVo;
import org.sziit.presentation.exception.InternalServerErrorException;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/8 21:37
 */
@Log4j2
@AllArgsConstructor
@RestController
@RequestMapping("/member/")
@Tag(name = "MemberController", description = "MEMBER_CONTROLLER")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("getMyPersonalInfo")
    @ValidationStatusCode(code = "400")
    public AccountVo getMyPersonalInfo(String memberId) {
        return memberService.getAccountInfo(memberId);
    }

    @PostMapping("updateNickName")
    @ValidationStatusCode(code = "400")
    public void updateNickName(@RequestBody MemberReqDto reqDto) {
        if (memberService.updateNickName(reqDto)) {
            log.info("updateNickName success");
        } else {
            log.info("updateNickName fail");
            throw new InternalServerErrorException("updateNickName fail");
        }
    }

    @GetMapping("findLoginLogByPage")
    @ValidationStatusCode(code = "400")
    public void findLoginLogByPage() {
        log.info("findLoginLogByPage");
    }
}
