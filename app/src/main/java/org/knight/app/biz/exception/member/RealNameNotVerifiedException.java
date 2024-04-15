package org.knight.app.biz.exception.member;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/8 11:43
 */
@ExceptionMapper(code = "400", msg = "BadRequest: Real Name Not Verified Exception")
public class RealNameNotVerifiedException extends RuntimeException{
    public RealNameNotVerifiedException(String message) {
        super(message);
    }
}
