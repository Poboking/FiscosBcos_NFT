package org.knight.app.biz.exception.transaction;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/12 15:42
 */
@ExceptionMapper(code = "400",msg = "BadRequest: Collection Already Resale")
public class CollectionAlreadyResaleException extends RuntimeException{
    public CollectionAlreadyResaleException(String message) {
        super(message);
    }
}
