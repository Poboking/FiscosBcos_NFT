package org.sziit.app.biz.account.dto.login.member;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/18 16:35
 */
@Builder
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginReqDTO extends LoginBaseDTO {

    @NotNull(message = "手机号不能为空")
    private String mobile;

    @Null
    private String verifyCode;

    @Null
    private String inviteCode;

    @NotNull(message = "密码不能为空")
    private String password;
}
