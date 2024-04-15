package org.knight.presentation.exception;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/13 14:51
 */
@ExceptionMapper(code = "400", msg = "Member Update Password Exception")
public class MemberUpdatePwdException extends RuntimeException{
    public MemberUpdatePwdException(String message) {
        super(message);
    }
}
