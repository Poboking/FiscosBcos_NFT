package org.knight.app.biz.exception.log;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/31 18:29
 */
@ExceptionMapper(code = "503", msg = "Log Creation Failed")
public class LogCreationFailedException extends RuntimeException{
    public LogCreationFailedException(String message) {
        super(message);
    }
}
