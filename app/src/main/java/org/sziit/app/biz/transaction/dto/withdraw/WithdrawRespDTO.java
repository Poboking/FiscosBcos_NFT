package org.sziit.app.biz.transaction.dto.withdraw;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.sziit.app.biz.transaction.dto.TransactionBaseDTO;
import org.sziit.app.biz.transaction.dto.settlement.SettlementAccountRespDTO;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/23 19:34
 */
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Builder
@Data
public class WithdrawRespDTO extends TransactionBaseDTO {

    private String id;

    private String orderNo;

    private Double amount;

    private Double handlingFee;

    private Double toTheAccount;

    private String submitTime;

    private String dealTime;

    private String state;

    private String stateName;

    private String note;

    private SettlementAccountRespDTO settlementAccount;

    private String memberMobile;

    private String memberBlockChainAddr;
}
