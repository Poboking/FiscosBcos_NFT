package org.knight.app.biz.transaction.dto.trade;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/24 16:02
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TradeStatisticRespDTO {
    private Double todayAmount;

    private Long todayCount;

    private Double yesterdayAmount;

    private Double totalAmount;
}
