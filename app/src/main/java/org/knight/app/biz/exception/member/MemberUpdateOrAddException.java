package org.knight.app.biz.exception.member;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/7 11:47
 */
@ExceptionMapper(code = "500", msg = "MemberError: Member update or add failed")
public class MemberUpdateOrAddException extends RuntimeException{
    public MemberUpdateOrAddException(String message) {
        super(message);
    }
}
