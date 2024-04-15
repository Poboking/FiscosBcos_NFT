package org.knight.app.biz.exception.transaction;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/14 0:12
 */
@ExceptionMapper(code = "500",msg = "ServerError: Member Balance Update Failed.")
public class MemberBalanceUpdateFailedException extends RuntimeException{
    public MemberBalanceUpdateFailedException(String message) {
        super(message);
    }
}
