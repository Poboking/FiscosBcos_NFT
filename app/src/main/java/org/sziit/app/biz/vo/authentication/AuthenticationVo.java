package org.sziit.app.biz.vo.authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/10 15:01
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationVo {

    private String accountId;

    private String tokenName;

    private String tokenValue;
}
