package org.knight.infrastructure.exception.initialize;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/8 0:21
 */
public class UserDataInitializeException extends RuntimeException{
    public UserDataInitializeException(String message) {
        super(message);
    }
}
