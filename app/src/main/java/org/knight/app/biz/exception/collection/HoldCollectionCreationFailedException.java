package org.knight.app.biz.exception.collection;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/5 14:56
 */
@ExceptionMapper(code = "500", msg = "Hold collection creation failed")
public class HoldCollectionCreationFailedException extends RuntimeException{
    public HoldCollectionCreationFailedException(String message) {
        super(message);
    }
}
