package org.knight.app.biz.exception.member;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/1 18:56
 */
@ExceptionMapper(code = "400", msg = "Member Don't Exist")
public class MemberNoFoundException extends RuntimeException {
    public MemberNoFoundException(String message) {
        super(message);
    }
}
