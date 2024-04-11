package org.knight.app.biz.transaction.dto.issued;

import lombok.*;
import org.knight.app.biz.transaction.dto.TransactionBaseDTO;

import java.sql.Timestamp;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/8 12:37
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CastIssuedCollectionReqDTO extends TransactionBaseDTO {
    /**
     * 发行收藏品的唯一标识ID
     */
    private String id;
    /**
     * 收藏品ID
     */
    private String collectionId;
    /**
     * 收藏品序列号
     */
    private Integer collectionSerialNumber;
    /**
     * 发行时间
     */
    private Timestamp issueTime;
    /**
     * 与区块链同步的时间
     */
    private Timestamp syncChainTime;
    /**
     * 唯一ID，用于在区块链上的唯一标识
     */
    private String uniqueId;
}
