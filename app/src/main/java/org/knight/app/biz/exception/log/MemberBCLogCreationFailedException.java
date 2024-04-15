package org.knight.app.biz.exception.log;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/12 21:05
 */
@ExceptionMapper(code = "500", msg = "ServerError: Create Member BC Log Failed")
public class MemberBCLogCreationFailedException extends RuntimeException{
    public MemberBCLogCreationFailedException(String message) {
        super(message);
    }
}
