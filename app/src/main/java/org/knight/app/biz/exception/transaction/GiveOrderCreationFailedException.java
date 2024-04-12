package org.knight.app.biz.exception.transaction;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/12 13:56
 */
@ExceptionMapper(code = "500", msg = "ServerError: Order of Give Collection Creation Failed")
public class GiveOrderCreationFailedException extends RuntimeException{
    public GiveOrderCreationFailedException(String message) {
        super(message);
    }
}
