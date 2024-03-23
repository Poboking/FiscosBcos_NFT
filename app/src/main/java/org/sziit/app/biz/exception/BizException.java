package org.sziit.app.biz.exception;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/16 16:42
 */
@ExceptionMapper(code = "503", msg = "Biz Service Unavailable")
public class BizException extends RuntimeException {
    public BizException(String message) {
        super(message);
    }
}
