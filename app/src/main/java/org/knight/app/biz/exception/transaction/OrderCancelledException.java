package org.knight.app.biz.exception.transaction;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/31 18:26
 */
@ExceptionMapper(code = "400", msg = "Order Already Cancelled")
public class OrderCancelledException extends RuntimeException{
    public OrderCancelledException(String message) {
        super(message);
    }
}
