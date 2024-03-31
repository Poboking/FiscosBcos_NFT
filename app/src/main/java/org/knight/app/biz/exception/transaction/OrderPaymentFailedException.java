package org.knight.app.biz.exception.transaction;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/31 18:28
 */
@ExceptionMapper(code = "400", msg = "Order Payment Failed")
public class OrderPaymentFailedException extends RuntimeException{
    public OrderPaymentFailedException(String message) {
        super(message);
    }
}
