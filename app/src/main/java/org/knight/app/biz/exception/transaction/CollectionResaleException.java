package org.knight.app.biz.exception.transaction;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/11 21:50
 */
@ExceptionMapper(code = "500", msg = "ServerError: Collection Resale Failed")
public class CollectionResaleException extends RuntimeException {
    public CollectionResaleException(String message) {
        super(message);
    }
}
