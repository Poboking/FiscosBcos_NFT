package org.sziit.presentation.member;

import cn.hutool.core.text.CharSequenceUtil;
import com.feiniaojin.gracefulresponse.api.ValidationStatusCode;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.sziit.app.biz.vo.authentication.AuthenticationVo;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/8 20:26
 */
@Log4j2
@RestController
@AllArgsConstructor
@Tag(name = "AuthenticationController", description = "AUTHENTICATION_CONTROLLER")
public class AuthenticationController {

    @GetMapping("/sendLoginVerificationCode")
    @ValidationStatusCode(code = "400")
    public void sendLoginVerificationCode(@RequestParam("mobile") String mobile) {
        log.info(CharSequenceUtil.format("mobile({}): sendLoginVerificationCode,", mobile));
    }

    @PostMapping("/login")
    @ValidationStatusCode(code = "400")
    public AuthenticationVo login(@RequestParam("mobile") String mobile, @RequestParam("verificationCode") String verificationCode) {
        log.info(CharSequenceUtil.format("mobile({}): login, verificationCode({})", mobile, verificationCode));
        return AuthenticationVo.builder()
                .tokenName("token-jwt")
                .tokenValue("token-value-example")
                .accountId("accountId-example")
                .build();
    }
}
