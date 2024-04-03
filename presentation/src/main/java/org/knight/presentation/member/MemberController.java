package org.knight.presentation.member;

import cn.hutool.core.text.CharSequenceUtil;
import com.feiniaojin.gracefulresponse.api.ValidationStatusCode;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.knight.app.biz.account.MemberService;
import org.knight.app.biz.account.dto.account.member.AccountRespDTO;
import org.knight.app.biz.account.dto.invite.InviteInfoRespDTO;
import org.knight.app.biz.account.dto.invite.InviteeRecordRespDTO;
import org.knight.app.biz.account.dto.member.MemberBindRealNameReqDTO;
import org.knight.app.biz.account.dto.member.MemberUpdateAvatarReqDTO;
import org.knight.app.biz.account.dto.member.MemberUpdateNickNameReqDTO;
import org.knight.app.biz.log.LoginLogService;
import org.knight.app.biz.log.dto.loginlog.LoginLogRespDTO;
import org.knight.infrastructure.common.PageResult;
import org.knight.presentation.exception.BadRequestException;
import org.knight.presentation.exception.InternalServerErrorException;
import org.knight.presentation.exception.UnauthorizedException;
import org.knight.presentation.utils.StpUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/8 21:37
 */
//@CheckUserLogin
@Log4j2
@RestController
@RequestMapping("/member/")
@Tag(name = "MemberController", description = "MEMBER_CONTROLLER")
public class MemberController {
    private MemberService memberService;
    private final LoginLogService loginLogService;

    @Autowired
    public MemberController(MemberService memberService, LoginLogService loginLogService) {
        this.memberService = memberService;
        this.loginLogService = loginLogService;
    }


    @GetMapping("getMyPersonalInfo")
    @ValidationStatusCode(code = "400")
    public AccountRespDTO getMyPersonalInfo() {
        String loginId = StpUserUtil.getLoginIdAsString();
        if (loginId.isEmpty()) {
            throw new UnauthorizedException("getMyPersonalInfo fail as a result of loginId is null");
        }
        return memberService.getAccountInfo(loginId);
    }


    /**
     * DTO类得加@NoArgsConstructor注解, 不然会出现JSON parse error!!(╬▔皿▔)╯
     */
    @PostMapping("updateNickName")
    @ValidationStatusCode(code = "400")
    public void updateNickName(@Valid @RequestBody MemberUpdateNickNameReqDTO reqDto) {
        String loginId = StpUserUtil.getLoginIdAsString();
        if (Boolean.FALSE.equals(loginId.isEmpty()) && memberService.updateNickName(reqDto, loginId)) {
            log.info("updateNickName success");
        } else {
            log.info("updateNickName fail");
            throw new InternalServerErrorException("updateNickName fail");
        }
    }

    @GetMapping("findLoginLogByPage")
    @ValidationStatusCode(code = "400")
    public PageResult<LoginLogRespDTO> findLoginLogByPage(
            @RequestParam(name = "current", defaultValue = "1") long current,
            @RequestParam(name = "pageSize", defaultValue = "10") long pageSize) {
        String userName = StpUserUtil.getLoginIdAsString();
        return loginLogService.getLoginLog(current, pageSize, userName);
    }

    @PostMapping("bindRealName")
    @ValidationStatusCode(code = "400")
    public void bindRealName(@RequestBody MemberBindRealNameReqDTO reqDto) {
        String loginId = StpUserUtil.getLoginIdAsString();
        if (Boolean.FALSE.equals(loginId.equals(memberService.getIdByMobile(reqDto.getMobile())))) {
            throw new BadRequestException("bindRealName fail as a result of mobile is not match");
        }
        if (Boolean.FALSE.equals(loginId.isEmpty()) && memberService.bindReadName(reqDto, loginId)) {
            log.info(CharSequenceUtil.format("{}: bindRealName success", reqDto.getMobile()));
        } else {
            log.info(CharSequenceUtil.format("{}: bindRealName fail", reqDto.getMobile()));
            throw new InternalServerErrorException("bindRealName fail");
        }
    }

    @PostMapping("updateAvatar")
    @ValidationStatusCode(code = "400")
    public void updateAvatar(@Valid @RequestBody MemberUpdateAvatarReqDTO reqDto) {
        String loginId = StpUserUtil.getLoginIdAsString();
        log.info(CharSequenceUtil.format("{}: updateAvatar", loginId));
        if (Boolean.FALSE.equals(loginId.isEmpty()) && memberService.updateAvatar(reqDto, loginId)) {
            log.info(CharSequenceUtil.format("updateAvatar success: {}", loginId));
        } else {
            log.info(CharSequenceUtil.format("updateAvatar fail: {}", loginId));
            throw new InternalServerErrorException(CharSequenceUtil.format("updateAvatar fail: {}", loginId));
        }
    }

    @GetMapping("getInviteInfo")
    @ValidationStatusCode(code = "400")
    public InviteInfoRespDTO getInviteInfo() {
        String memberId = StpUserUtil.getLoginIdAsString();
        if (memberId == null) {
            log.info("getInviteInfo fail because loginId can not be null");
            throw new InternalServerErrorException("getInviteInfo fail");
        }
        return memberService.getInviteInfo(memberId);
    }

    @GetMapping("findMyInviteRecord")
    @ValidationStatusCode(code = "400")
    public List<InviteeRecordRespDTO> findMyInviteRecord() {
        String memberId = StpUserUtil.getLoginIdAsString();
        if (memberId == null) {
            log.info("findMyInviteRecord fail because loginId can not be null");
            throw new InternalServerErrorException("findMyInviteRecord fail");
        }
        return memberService.getInviteeRecord(memberId);
    }

    @GetMapping("getBalance")
    @ValidationStatusCode(code = "400")
    public Map<String, Double> getBalance() {
        String memberId = StpUserUtil.getLoginIdAsString();
        if (memberId == null) {
            log.info("getBalance fail because loginId can not be null");
            throw new InternalServerErrorException("getBalance fail as result of loginId is null");
        }
        Double blance = memberService.getBlance(memberId);
        if (blance == null) {
            log.info("getBalance fail because balance can not be null");
            throw new InternalServerErrorException("getBalance fail as a result of balance is null");
        }
        return Collections.singletonMap("balance", blance);
    }
}
