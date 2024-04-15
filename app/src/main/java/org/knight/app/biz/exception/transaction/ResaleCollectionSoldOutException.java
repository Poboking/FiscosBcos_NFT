package org.knight.app.biz.exception.transaction;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/14 0:35
 */
@ExceptionMapper(code = "500", msg = "ServerError: Resale Collection Sold Out")
public class ResaleCollectionSoldOutException extends RuntimeException {
    public ResaleCollectionSoldOutException(String message) {
        super(message);
    }
}
