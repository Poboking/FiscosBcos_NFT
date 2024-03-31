package org.knight.app.biz.exception.transaction;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/31 18:27
 */
@ExceptionMapper(code = "400", msg = "Order Expired")
public class OrderExpiredException extends RuntimeException{
    public OrderExpiredException(String message) {
        super(message);
    }
}
