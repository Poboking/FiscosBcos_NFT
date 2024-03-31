package org.knight.app.biz.exception.collection;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/31 18:31
 */
@ExceptionMapper(code = "400", msg = "Collection Not Found")
public class CollectionNotFoundException extends RuntimeException{
    public CollectionNotFoundException(String message) {
        super(message);
    }
}
