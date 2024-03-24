package org.sziit.presentation.member;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.hutool.core.text.CharSequenceUtil;
import com.feiniaojin.gracefulresponse.api.ValidationStatusCode;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.log4j.Log4j2;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.sziit.app.biz.account.MemberService;
import org.sziit.app.biz.account.dto.authentication.AuthenticationRespDTO;
import org.sziit.app.biz.account.dto.login.member.LoginReqDTO;
import org.sziit.app.biz.account.dto.login.member.QuickLoginReqDTO;
import org.sziit.app.biz.log.LoginLogService;
import org.sziit.app.biz.log.dto.loginlog.LoginLogReqDTO;
import org.sziit.app.biz.log.dto.loginlog.LoginLogReqParam;
import org.sziit.presentation.exception.UnauthorizedException;
import org.sziit.presentation.utils.StpUserUtil;

import java.util.Collections;
import java.util.Map;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/8 20:26
 */
@Log4j2
@RestController
@Validated
@Tag(name = "AuthenticationController", description = "AUTHENTICATION_CONTROLLER")
public class AuthenticationController {
    private final MemberService memberService;

    private final LoginLogService loginLogService;

    private final String BROWSER = "User-Agent";

    private final String OS = "User-Os";

    @Autowired
    public AuthenticationController(MemberService memberService, LoginLogService loginLogService) {
        this.memberService = memberService;
        this.loginLogService = loginLogService;
    }


    // TODO: 2024/3/17 17:00 发送手机验证码 - 待实现 -  需要使用到短信付费服务 - 暂时使用固定验证码
    @GetMapping("/sendLoginVerificationCode")
    @ValidationStatusCode(code = "400")
    // 对于requestParam/PathVariable参数校验, 需在Controller类添加@Validated注解 (＠_＠;)
    public Map<String, String> sendLoginVerificationCode(@RequestParam("mobile") @Length(min = 11, max = 11) String mobile) {
        return Collections.singletonMap("verificationCode", "6666");
    }

    // TODO: 2024/3/18 15:52 验证码登录 - 待实现 - 暂时使用pwd登录
    @PostMapping("/login")
    @ValidationStatusCode(code = "400")
    public AuthenticationRespDTO login(
            @Valid @RequestBody @NotNull(message = "loginPram cannot be empty") LoginReqDTO loginParam,
            HttpServletRequest request) {
        LoginLogReqParam param = new LoginLogReqParam(request.getRemoteAddr(), request.getHeader(BROWSER), request.getHeader(OS), loginParam.getMobile());
        /*
          注册功能
         */
        if (Boolean.FALSE.equals(memberService.checkMobileExist(loginParam.getMobile()))) {
            log.info(CharSequenceUtil.format("Create User({})", loginParam.getMobile()));
            if (Boolean.FALSE.equals(memberService.registerUser(loginParam.getMobile(), loginParam.getPassword(), loginParam.getInviteCode()))) {
                throw new IndexOutOfBoundsException("register failed as result of registerUser.saved() failed");
            }
        }
        /*
            登录功能
         */
        // TODO: 2024/3/22 由前端获取数据"User-Os"放在请求头中
        boolean isLoginSuccess = memberService.LoginByCheckPwd(loginParam.getMobile(), loginParam.getPassword());
        if (Boolean.TRUE.equals(isLoginSuccess)) {
            String loginId = memberService.getIdByMobile(loginParam.getMobile());
            if (loginId.equals("-1")) {
                loginLogService.saveLoginLog(LoginLogReqDTO.quickFailureBuild(param));
                throw new UnauthorizedException("login failed as result of gain loginId failed");
            }
            StpUserUtil.login(loginId, 60 * 60 * 24 * 7);
            SaTokenInfo tokenInfo = StpUserUtil.getTokenInfo();
            loginLogService.saveLoginLog(LoginLogReqDTO.quickSuccessfulBuild(param));
            return AuthenticationRespDTO.builder()
                    .tokenName(tokenInfo.getTokenName())
                    .tokenValue(tokenInfo.getTokenValue())
                    .accountId(loginId)
                    .build();
        }
        loginLogService.saveLoginLog(LoginLogReqDTO.quickFailureBuild(param));
        throw new UnauthorizedException("login failed");
    }


    @PostMapping("quickLogin")
    @ValidationStatusCode(code = "400")
    public AuthenticationRespDTO quickLogin(
            @Valid @RequestBody @NotNull(message = "loginPram cannot be empty") QuickLoginReqDTO loginParam,
            HttpServletRequest request) {
        LoginLogReqParam param = new LoginLogReqParam(request.getRemoteAddr(), request.getHeader(BROWSER), request.getHeader(OS), loginParam.getMobile());
        if (Boolean.FALSE.equals(memberService.checkMobileExist(loginParam.getMobile()))) {
            /*
                注册功能
             */
            if (Boolean.FALSE.equals(memberService.registerUser(loginParam.getMobile()))) {
                loginLogService.saveLoginLog(LoginLogReqDTO.quickFailureBuild(param));
                throw new UnauthorizedException("login failed as result of registerUser.saved() failed");
            }
        }
        /*
            登录功能
         */
        String loginId = memberService.getIdByMobile(loginParam.getMobile());
        if (loginId.equals("-1")) {
            loginLogService.saveLoginLog(LoginLogReqDTO.quickFailureBuild(param));
            throw new UnauthorizedException("login failed as result of gain loginId failed");
        }
        StpUserUtil.login(loginId, 60 * 60 * 24 * 7);
        SaTokenInfo tokenInfo = StpUserUtil.getTokenInfo();
        loginLogService.saveLoginLog(LoginLogReqDTO.quickSuccessfulBuild(param));
        return AuthenticationRespDTO.builder()
                .tokenName(tokenInfo.getTokenName())
                .tokenValue(tokenInfo.getTokenValue())
                .accountId(loginId)
                .build();
    }

    @PostMapping("/logout")
    @ValidationStatusCode(code = "400")
    public Map<String, Boolean> logout() {
        if (Boolean.FALSE.equals(StpUserUtil.isLogin())) {
            throw new UnauthorizedException("logout failed as result of not login");
        }
        StpUserUtil.logout();
        return Collections.singletonMap("logout", true);
    }
}
