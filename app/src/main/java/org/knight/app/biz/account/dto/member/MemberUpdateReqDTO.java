package org.knight.app.biz.account.dto.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/12 21:54
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
public class MemberUpdateReqDTO extends MemberBaseDTO {
}
