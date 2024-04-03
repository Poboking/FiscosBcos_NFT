package org.knight.app.biz.transaction.dto.member;

import lombok.*;
import org.knight.app.biz.transaction.dto.TransactionBaseDTO;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/1 18:48
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReceiverInfoRespDTO extends TransactionBaseDTO {

    private String mobile;

    private String blockChainAddr;
}
