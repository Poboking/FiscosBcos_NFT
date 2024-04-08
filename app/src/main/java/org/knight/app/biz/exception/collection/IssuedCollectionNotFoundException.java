package org.knight.app.biz.exception.collection;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/5 16:43
 */
@ExceptionMapper(code = "400", msg = "IssuedCollection Not Found")
public class IssuedCollectionNotFoundException extends RuntimeException{
    public IssuedCollectionNotFoundException(String message) {
        super(message);
    }
}
