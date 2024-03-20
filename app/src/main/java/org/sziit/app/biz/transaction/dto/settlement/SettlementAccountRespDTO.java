package org.sziit.app.biz.transaction.dto.settlement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/20 22:24
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SettlementAccountRespDTO {

    private String id;

    private String readName;

    private String type;

    private String typeName;

    private Timestamp createTime;

    private Timestamp activatedTime;

    private String cardNumber;

    private String bankName;

    private String account;
}
