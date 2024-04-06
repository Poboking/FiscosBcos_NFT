package org.knight.app.biz.exception.collection;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/6 16:09
 */
@ExceptionMapper(code = "500", msg = "Gain Hold Collection Detail Failed")
public class HoldCollectionDetailFailedException extends RuntimeException{
    public HoldCollectionDetailFailedException(String message) {
        super(message);
    }
}
