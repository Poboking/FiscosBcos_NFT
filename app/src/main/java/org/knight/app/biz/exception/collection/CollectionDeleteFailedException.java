package org.knight.app.biz.exception.collection;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/9 21:24
 */
@ExceptionMapper(code = "500", msg = "ServerError: Collection Delete Failed")
public class CollectionDeleteFailedException extends RuntimeException{
    public CollectionDeleteFailedException(String message) {
        super(message);
    }
}
