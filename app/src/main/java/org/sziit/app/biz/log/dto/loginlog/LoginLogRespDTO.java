package org.sziit.app.biz.log.dto.loginlog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/13 14:19
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginLogRespDTO extends LoginLogBaseDTO {

    /**
     * LoginLog ID, example: 1767787746908176384
     */
    private String id;

    /**
     * Subsystem (member&admin), example: member
     */
    private String subSystem;

    /**
     * SubsystemName, example: 会员端
     */
    private String subSystemName;

    /**
     * State, example: 1(启用), 0(禁用)
     */
    private String state;

    /**
     * StateName, example: 成功
     */
    private String stateName;

    /**
     * IP Address, example: 114.246.202.194
     */
    private String ipAddr;

    /**
     * Login Time, example: 2024-03-13 13:40:17
     */
    private Timestamp loginTime;

    /**
     * Browser, example: MSEdge
     */
    private String browser;

    /**
     * OS, example: Android
     */
    private String os;

    /**
     * Message, example: 登录成功
     */
    private String msg;

    /**
     * userName, example: 11111111111, 一般以手机号作为用户名
     */
    private String userName;

}
