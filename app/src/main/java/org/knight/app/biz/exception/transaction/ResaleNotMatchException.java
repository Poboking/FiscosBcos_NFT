package org.knight.app.biz.exception.transaction;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/10 1:22
 */
@ExceptionMapper(code = "400", msg = "DataError: Resale not match")
public class ResaleNotMatchException extends RuntimeException{
    public ResaleNotMatchException(String message) {
        super(message);
    }
}
