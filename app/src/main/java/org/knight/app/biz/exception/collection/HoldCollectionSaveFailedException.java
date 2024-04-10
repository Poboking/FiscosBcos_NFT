package org.knight.app.biz.exception.collection;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/10 13:07
 */
@ExceptionMapper(code = "500", msg = "ServerError: Hold collection save failed")
public class HoldCollectionSaveFailedException extends RuntimeException{
    public HoldCollectionSaveFailedException(String message) {
        super(message);
    }
}
