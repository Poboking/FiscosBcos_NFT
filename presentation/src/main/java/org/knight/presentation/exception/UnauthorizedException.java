package org.knight.presentation.exception;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/9 23:49
 */
/*
 * Unauthorized异常(401), 用于未授权, 例如token过期, token无效, token缺失等
 * 由Graceful-Response框架统一处理, 进行异常捕获
 */
@ExceptionMapper(code = "401", msg = "Unauthorized")
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
