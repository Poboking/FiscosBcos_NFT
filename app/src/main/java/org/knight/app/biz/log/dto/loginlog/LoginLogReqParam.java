package org.knight.app.biz.log.dto.loginlog;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/22 11:01
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
public class LoginLogReqParam extends LoginLogBaseDTO {

    private String ipAddr;

    private String browser;

    private String os;

    private String userName;
}
