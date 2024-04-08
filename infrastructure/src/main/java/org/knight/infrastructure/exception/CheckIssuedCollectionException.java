package org.knight.infrastructure.exception;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/7 22:09
 */
public class CheckIssuedCollectionException extends RuntimeException{
    public CheckIssuedCollectionException(String message) {
        super(message);
    }
}
