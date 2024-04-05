package org.knight.app.biz.exception;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/4 15:25
 */
@ExceptionMapper(code = "400", msg = "Null Parameter Exception")
public class NullParameterException extends RuntimeException{
    public NullParameterException(String message) {
        super(message);
    }
}
