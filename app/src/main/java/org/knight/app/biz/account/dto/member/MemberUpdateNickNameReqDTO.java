package org.knight.app.biz.account.dto.member;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/20 16:50
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberUpdateNickNameReqDTO extends MemberBaseDTO {

    @NotNull
    @NotBlank
    private String nickName;
}
