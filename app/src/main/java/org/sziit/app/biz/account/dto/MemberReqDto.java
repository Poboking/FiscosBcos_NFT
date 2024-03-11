package org.sziit.app.biz.account.dto;

import lombok.*;

import java.time.LocalDateTime;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/9 23:21
 */

@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class MemberReqDto extends MemberBaseDto {

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

    private LocalDateTime registeredTime;

    private LocalDateTime latelyLoginTime;

    private LocalDateTime bindRealNameTime;

    private String inviterMobile;
}
