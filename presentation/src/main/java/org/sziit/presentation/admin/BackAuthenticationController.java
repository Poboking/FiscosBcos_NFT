package org.sziit.presentation.admin;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.feiniaojin.gracefulresponse.api.ValidationStatusCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.sziit.app.biz.account.BackgroundAccountService;
import org.sziit.app.biz.account.dto.authentication.AuthenticationRespDTO;
import org.sziit.app.biz.account.dto.login.background.BackLoginReqDTO;
import org.sziit.app.biz.log.LoginLogService;
import org.sziit.app.biz.log.dto.loginlog.LoginLogReqDTO;
import org.sziit.app.biz.log.dto.loginlog.LoginLogReqParam;
import org.sziit.presentation.exception.BadRequestException;
import org.sziit.presentation.utils.StpAdminUtil;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/24 0:28
 */
@Log4j2
@Validated
@RestController
public class BackAuthenticationController {

    private final BackgroundAccountService backgroundService;

    private final LoginLogService logService;

    private final String BROWSER = "User-Agent";

    private final String OS = "User-Os";

    @Autowired
    public BackAuthenticationController(BackgroundAccountService backgroundService, LoginLogService logService) {
        this.backgroundService = backgroundService;
        this.logService = logService;
    }

    @PostMapping("/back/login")
    @ValidationStatusCode(code = "400")
    public AuthenticationRespDTO login(
            @Valid @RequestBody BackLoginReqDTO loginParam,
            HttpServletRequest request) {
        if (Boolean.FALSE.equals(loginParam.getUserName().isEmpty() || loginParam.getLoginPwd().isEmpty())) {
            throw new BadRequestException("参数错误");
        }
        LoginLogReqParam param = new LoginLogReqParam(request.getRemoteAddr(), request.getHeader(BROWSER), request.getHeader(OS), loginParam.getUserName());
        if (Boolean.FALSE.equals(backgroundService.checkExist(loginParam.getUserName()))) {
            logService.saveLoginLog(LoginLogReqDTO.quickFailureBuild(param));
            throw new BadRequestException("用户不存在");
        }
        if (Boolean.FALSE.equals(backgroundService.checkPwd(loginParam.getUserName(), loginParam.getLoginPwd()))) {
            logService.saveLoginLog(LoginLogReqDTO.quickFailureBuild(param));
            throw new BadRequestException("密码错误");
        }
        String loginId = backgroundService.getLoginId(loginParam.getUserName());
        StpAdminUtil.login(loginId);
        SaTokenInfo tokenInfo = StpAdminUtil.getTokenInfo();
        logService.saveLoginLog(LoginLogReqDTO.quickFailureBuild(param));
        return AuthenticationRespDTO.builder()
                .accountId(loginId)
                .tokenName(tokenInfo.getTokenName())
                .tokenValue(tokenInfo.getTokenValue())
                .build();
    }
}
