package org.knight.app.biz.transaction.dto.order;

import lombok.*;
import org.knight.app.biz.transaction.dto.TransactionBaseDTO;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/15 16:01
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PayOrderRespDTO extends TransactionBaseDTO {

    private String id;

    private String orderNo;

    private String commodityType;

    private String commodityTypeName;

    private String createTime;

    private String paidTime;

    private String cancelTime;

    private Double amount;

    private String bizMode;

    private String bizModeName;

    private String bizOrderNo;

    private String state;

    private String stateName;

    private String collectionName;

    private String collectionCover;

    private String creatorName;

    private String memberMobile;

    private String memberBlockChainAddr;
}
