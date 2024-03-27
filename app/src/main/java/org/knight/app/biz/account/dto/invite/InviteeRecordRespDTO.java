package org.knight.app.biz.account.dto.invite;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/17 20:44
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InviteeRecordRespDTO {

    private String mobile;

    private Boolean inviteSuccessFlag;

    private Boolean boughtFlag;

    public static InviteeRecordRespDTO create(String mobile, Boolean inviteSuccessFlag, Boolean boughtFlag) {
        return InviteeRecordRespDTO.builder()
                .mobile(mobile)
                .inviteSuccessFlag(inviteSuccessFlag)
                .boughtFlag(boughtFlag)
                .build();
    }
}
