package org.sziit.app.biz.account.dto.login.background;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/24 14:22
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BackLoginReqDTO {

    @NotNull
    private String userName;

    @NotNull
    private String loginPwd;
}
