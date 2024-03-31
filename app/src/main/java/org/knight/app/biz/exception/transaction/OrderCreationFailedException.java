package org.knight.app.biz.exception.transaction;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/31 18:31
 */
@ExceptionMapper(code = "500", msg = "Order Creation Failed")
public class OrderCreationFailedException extends RuntimeException{
    public OrderCreationFailedException(String message) {
        super(message);
    }
}
