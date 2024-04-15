package org.knight.app.biz.exception.param;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/15 16:30
 */
@ExceptionMapper(code = "400",msg = "BadRequest: Illehal date provided.")
public class IllegalDateException extends RuntimeException {
    public IllegalDateException() {
        super("Invalid date provided.");
    }

    public IllegalDateException(String message) {
        super(message);
    }

    public IllegalDateException(String message, Throwable cause) {
        super(message, cause);
    }
}
