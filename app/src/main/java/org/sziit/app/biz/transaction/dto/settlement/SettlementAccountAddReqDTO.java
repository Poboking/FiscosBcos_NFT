package org.sziit.app.biz.transaction.dto.settlement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/20 22:20
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SettlementAccountAddReqDTO {

    private String bankName;

    private Integer cardNumber;

    /**
     * 1:银行卡 - bankCard 2:支付宝 - alipay 3:微信 - wechat
     */
    private String type;

    private String account;

}
