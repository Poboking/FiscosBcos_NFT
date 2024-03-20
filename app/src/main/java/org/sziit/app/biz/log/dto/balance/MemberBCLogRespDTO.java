package org.sziit.app.biz.log.dto.balance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/17 22:42
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberBCLogRespDTO {
    private String id;

    private LocalDateTime changeTime;

    private String changeType;

    private String changeTypeName;

    private Double balanceChange;
}
