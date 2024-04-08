package org.knight.infrastructure.fisco.module;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/5 14:37
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlockChainUser {

    private String memberId;

    private String blockChainAddress;

}
