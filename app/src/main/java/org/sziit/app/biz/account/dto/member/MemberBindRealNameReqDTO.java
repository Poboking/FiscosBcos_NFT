package org.sziit.app.biz.account.dto.member;

import lombok.*;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/12 21:51
 */
@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class MemberBindRealNameReqDTO extends MemberBaseDTO {

    private String realName;

    private String ssn;

    private String mobile;
}
