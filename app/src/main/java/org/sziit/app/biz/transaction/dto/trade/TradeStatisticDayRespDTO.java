package org.sziit.app.biz.transaction.dto.trade;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/24 16:09
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TradeStatisticDayRespDTO {

    private String everyday;

    private Double successAmount;

    private Long successCount;
}
