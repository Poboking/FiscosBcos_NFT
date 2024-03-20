package org.sziit.app.biz.account.dto.member;

import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/13 16:07
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberUpdateAvatarReqDTO extends MemberBaseDTO {
    @NotNull
    private String avatar;
}
