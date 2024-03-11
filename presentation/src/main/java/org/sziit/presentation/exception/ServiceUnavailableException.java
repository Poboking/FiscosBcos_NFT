package org.sziit.presentation.exception;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/9 23:47
 */
/*
 * Service Unavailable异常(503), 用于服务不可用, 例如数据库连接失败, 服务宕机, 服务超时, 服务过载等
 * 由Graceful-Response框架统一处理, 进行异常捕获
 */
@ExceptionMapper(code = "503", msg = "Service Unavailable")
public class ServiceUnavailableException extends RuntimeException {
    public ServiceUnavailableException(String message) {
        super(message);
    }
}
