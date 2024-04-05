package org.knight.infrastructure.exception;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/5 0:39
 */

public class BlockChainException extends RuntimeException {
    public BlockChainException(String message) {
        super(message);
    }
}
