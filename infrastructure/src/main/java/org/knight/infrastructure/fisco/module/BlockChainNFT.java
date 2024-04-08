package org.knight.infrastructure.fisco.module;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/5 14:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlockChainNFT {
    private String uniqueId;

    private String issuedCollectionId;

    private String transactionHash;

    private Timestamp syncChainTime;
}
