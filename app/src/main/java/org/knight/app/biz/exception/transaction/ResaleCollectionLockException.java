package org.knight.app.biz.exception.transaction;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/13 14:02
 */
@ExceptionMapper(code = "500", msg = "ServiceError: Resale Collection Lock Failed")
public class ResaleCollectionLockException extends RuntimeException{
    public ResaleCollectionLockException(String message) {
        super(message);
    }
}
