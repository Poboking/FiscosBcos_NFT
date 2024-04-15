package org.knight.app.biz.exception.collection;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/13 12:58
 */
@ExceptionMapper(code = "500", msg = "ServiceError: Member Hold Collection Update State Failed")
public class MemberHoldCollectionUpdateStateFailedException extends RuntimeException{
    public MemberHoldCollectionUpdateStateFailedException(String message) {
        super(message);
    }
}
