package org.knight.app.biz.exception.transaction;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/13 23:54
 */
@ExceptionMapper(code = "500",msg = "ServerError: Resale Collection Update Status Failed.")
public class ResaleCollectionUpdateFailedException extends RuntimeException{
    public ResaleCollectionUpdateFailedException(String message) {
        super(message);
    }
}
