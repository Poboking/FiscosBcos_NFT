package org.knight.app.biz.exception.transaction;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/13 0:39
 */
@ExceptionMapper(code = "400", msg = "inventory shortage")
public class CollectionStockNotEnoughException extends RuntimeException{
    public CollectionStockNotEnoughException(String message) {
        super(message);
    }
}
