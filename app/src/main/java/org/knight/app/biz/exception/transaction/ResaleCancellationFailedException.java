package org.knight.app.biz.exception.transaction;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/6 16:25
 */
@ExceptionMapper(code = "500", msg = "Resale Cancellation Failed")
public class ResaleCancellationFailedException extends RuntimeException{
    public ResaleCancellationFailedException(String message) {
        super(message);
    }
}
