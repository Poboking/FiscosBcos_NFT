package org.knight.app.biz.exception.collection;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/8 12:26
 */
@ExceptionMapper(code = "500", msg = "ServiceError: IssuedCollection Cast Failed Exception")
public class IssuedCollectionCastFailedException extends RuntimeException{
    public IssuedCollectionCastFailedException(String message) {
        super(message);
    }
}
