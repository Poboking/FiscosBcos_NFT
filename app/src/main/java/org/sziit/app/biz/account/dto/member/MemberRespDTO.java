package org.sziit.app.biz.account.dto.member;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/16 15:23
 */
public class MemberRespDTO extends MemberBaseDTO{

    private String id;

    private String realName;

    private String identityCard;

    private String mobile;

    private String nickName;

    private String avatar;

    private String blockChainAddr;

    private String state;

    private String stateName;

    private Double balance;

    private Boolean boughtFlag;

    private String inviteCode;

    private Boolean notSetPayPwd;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date registeredTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date latelyLoginTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date bindRealNameTime;

    private String inviterMobile;

}
