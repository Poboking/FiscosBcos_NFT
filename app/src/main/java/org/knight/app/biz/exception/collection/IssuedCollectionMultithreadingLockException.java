package org.knight.app.biz.exception.collection;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/5 16:21
 */
@ExceptionMapper(code = "503", msg = "Multithreading Error: Issued Collection Already Locked")
public class IssuedCollectionMultithreadingLockException extends RuntimeException{
    public IssuedCollectionMultithreadingLockException(String message) {
        super(message);
    }
}
