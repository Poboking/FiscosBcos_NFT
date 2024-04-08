package org.knight.infrastructure.exception.initialize;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/8 0:21
 */
public class IssuedCollectionDataInitializeException extends RuntimeException{
    public IssuedCollectionDataInitializeException(String message) {
        super(message);
    }
}
