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
public class MyPayOrderRespDTO extends TransactionBaseDTO {

    private String id;

    private String orderNo;

    private String commodityType;

    private Double amount;

    private String state;

    private String stateName;

    private String collectionName;

    private String collectionCover;

    private String creatorName;

    private LocalDateTime paidTime;

    private LocalDateTime createTime;
}
