package org.knight.infrastructure.exception.initialize;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/10 15:02
 */
public class BlockChainInitDataFailedException extends RuntimeException{
    public BlockChainInitDataFailedException(String message) {
        super(message);
    }
}
