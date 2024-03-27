package org.knight.app.biz.account.dto.member;

import lombok.*;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/24 15:12
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberStatisticDataRespDTO extends MemberBaseDTO {

    private Long accountCount;

    private Long realNameCount;

    private Long todayRegisterCount;

}
