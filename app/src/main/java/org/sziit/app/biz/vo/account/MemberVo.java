package org.sziit.app.biz.vo.account;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/8 22:36
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberVo {

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
