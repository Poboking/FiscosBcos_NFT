package org.knight.app.biz.exception.transaction;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/3 23:55
 */
@ExceptionMapper(code = "503", msg = "Order Cancellation Failed")
public class OrderCancellationFailedException extends RuntimeException{
    public OrderCancellationFailedException(String message) {
        super(message);
    }
}
