package org.knight.app.biz.exception.collection;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/14 16:53
 */
@ExceptionMapper(code = "500", msg = "ServerError: Member hold collection update failed")
public class MemberHoldCollectionUpdateFailedException extends RuntimeException{
    public MemberHoldCollectionUpdateFailedException(String message) {
        super(message);
    }
}
