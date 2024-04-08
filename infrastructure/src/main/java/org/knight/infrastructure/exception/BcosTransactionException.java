package org.knight.infrastructure.exception;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/7 22:13
 */

public class BcosTransactionException extends RuntimeException{
    public BcosTransactionException(String message) {
        super(message);
    }
}
