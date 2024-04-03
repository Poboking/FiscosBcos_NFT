package org.knight.app.biz.exception.member;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/3 12:14
 */
@ExceptionMapper(code = "503", msg = "Multiple mobile phone numbers exist in the system. Please contact customer service.")
public class CheckMobileExistException extends RuntimeException {
    public CheckMobileExistException(String message) {
        super(message);
    }
}
