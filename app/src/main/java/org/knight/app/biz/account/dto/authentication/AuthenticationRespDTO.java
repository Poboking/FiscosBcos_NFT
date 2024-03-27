package org.knight.app.biz.account.dto.authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/17 18:49
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRespDTO {

    private String accountId;

    private String tokenName;

    private String tokenValue;
}
