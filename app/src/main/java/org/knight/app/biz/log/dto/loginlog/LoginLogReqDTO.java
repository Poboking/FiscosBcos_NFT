package org.knight.app.biz.log.dto.loginlog;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.knight.infrastructure.common.IdUtils;

import java.sql.Timestamp;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/13 14:31
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
public class LoginLogReqDTO extends LoginLogBaseDTO {
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
     * State, example: 1
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


    public static LoginLogReqDTO quickSuccessfulBuild(String ipAddr, String browser, String os, String userName) {
        String subSystem = "member"; // 设置 subsystem
        String subSystemName = "会员端"; // 设置 subsystemName
        String state = "1"; // 设置 state
        String stateName = "成功"; // 设置 stateName
        Timestamp loginTime = new Timestamp(System.currentTimeMillis()); // 获取当前时间作为登录时间
        String msg = "登录成功"; // 设置消息

        return LoginLogReqDTO.builder()
                .id(IdUtils.snowFlakeId())
                .subSystem(subSystem)
                .subSystemName(subSystemName)
                .state(state)
                .stateName(stateName)
                .ipAddr(ipAddr)
                .loginTime(loginTime)
                .browser(browser)
                .os(os)
                .msg(msg)
                .userName(userName)
                .build();
    }

    public static LoginLogReqDTO quickSuccessfulBuild(LoginLogReqParam param) {
        return quickSuccessfulBuild(param.getIpAddr(), param.getBrowser(), param.getOs(), param.getMobile());
    }


    public static LoginLogReqDTO quickFailureBuild(String ipAddr, String browser, String os, String moblie) {
        String subSystem = "member"; // 设置 subsystem
        String subSystemName = "会员端"; // 设置 subsystemName
        String state = "1"; // 设置 state
        String stateName = "失败"; // 设置 stateName
        Timestamp loginTime = new Timestamp(System.currentTimeMillis()); // 获取当前时间作为登录时间
        String msg = "登录失败"; // 设置消息

        return LoginLogReqDTO.builder()
                .id(IdUtils.snowFlakeId())
                .subSystem(subSystem)
                .subSystemName(subSystemName)
                .state(state)
                .stateName(stateName)
                .ipAddr(ipAddr)
                .loginTime(loginTime)
                .browser(browser)
                .os(os)
                .msg(msg)
                .userName(moblie)
                .build();
    }

    public static LoginLogReqDTO quickFailureBuild(LoginLogReqParam param) {
        return quickFailureBuild(param.getIpAddr(), param.getBrowser(), param.getOs(), param.getMobile());
    }

    public static LoginLogReqDTO quickBuild(Boolean isSucceed, String ipAddr, String browser, String os, String moblie) {
        if (Boolean.TRUE.equals(isSucceed)) {
            return quickSuccessfulBuild(ipAddr, browser, os, moblie);
        } else {
            return quickFailureBuild(ipAddr, browser, os, moblie);
        }
    }

    public static LoginLogReqDTO quickBuild(Boolean isSucceed, LoginLogReqParam param) {
        if (Boolean.TRUE.equals(isSucceed)) {
            return quickSuccessfulBuild(param);
        } else {
            return quickFailureBuild(param);
        }
    }

}
