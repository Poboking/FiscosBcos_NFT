package org.sziit.presentation.exception;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/9 23:39
 */

/*
 * Internal Server Error异常(500), 用于服务内部错误
 * 由Graceful-Response框架统一处理, 进行异常捕获
 */
@ExceptionMapper(code = "500", msg = "Service Error")
public class InternalServerErrorException extends RuntimeException {
    public InternalServerErrorException(String message) {
        super(message);
    }
}
