package org.knight.app.biz.exception.collection;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/31 22:28
 */
@ExceptionMapper(code = "500", msg = "Resale Collection Detail Gain Failed")
public class ResaleCollectionDetailFailedException extends RuntimeException{
    public ResaleCollectionDetailFailedException(String message) {
        super(message);
    }
}
