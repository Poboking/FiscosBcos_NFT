package org.knight.app.biz.transaction.dto.order;

import lombok.*;
import org.knight.app.biz.transaction.dto.TransactionBaseDTO;

import java.time.LocalDateTime;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/17 23:39
 */
@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayOrderRespDTO extends TransactionBaseDTO {

    private Double amount;

    private String state;

    private LocalDateTime paidTime;

    private LocalDateTime createTime;
}
