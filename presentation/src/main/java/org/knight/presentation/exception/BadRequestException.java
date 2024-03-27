package org.knight.presentation.exception;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/9 23:36
 */

/*
 * Bad Request异常(400), 用于参数校验失败
 * 由Graceful-Response框架统一处理, 进行异常捕获
 */
@ExceptionMapper(code = "400", msg = "Bad Request")
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
