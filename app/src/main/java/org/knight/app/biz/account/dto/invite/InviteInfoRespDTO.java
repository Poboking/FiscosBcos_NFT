package org.knight.app.biz.account.dto.invite;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/17 20:27
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InviteInfoRespDTO {

    public static final String inviteBaseLink = "http://45.125.34.58:9999/#/pages/login/login?inviteCode=";

    private String inviteCode;

    private String inviteLink;

    public static InviteInfoRespDTO create(String inviteCode) {
        return InviteInfoRespDTO.builder()
                .inviteCode(inviteCode)
                .inviteLink(inviteBaseLink + inviteCode)
                .build();
    }
}
