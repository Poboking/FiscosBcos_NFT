package org.knight.app.biz.exception.transaction;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/6 16:33
 */
@ExceptionMapper(code = "400", msg = "Insufficient Privileges: MemberId Not Match")
public class MemberNotMatchException extends RuntimeException{
    public MemberNotMatchException(String message) {
        super(message);
    }
}
