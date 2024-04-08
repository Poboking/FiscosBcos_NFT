package org.knight.app.biz.exception.transaction;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/8 11:24
 */
@ExceptionMapper(code = "500", msg = "ServiceError: IssuedCollection Action Log Update Or Add Exception")
public class IssuedCollectionActLogUpdateOrAddException extends RuntimeException{
    public IssuedCollectionActLogUpdateOrAddException(String message) {
        super(message);
    }
}
