package org.sziit.app.biz.transaction.dto.settlement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/20 22:33
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SettlementAccountUpdateReqDTO {

    @NotNull
    private String accountId;

    @NotNull
    private Boolean activated;
}
