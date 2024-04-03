package org.knight.app.biz.exception.transaction;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/3 20:41
 */
@ExceptionMapper(code = "400", msg = "Order Timeout Exception")
public class OrderTimeoutException extends RuntimeException{
    public OrderTimeoutException(String message) {
        super(message);
    }
}
