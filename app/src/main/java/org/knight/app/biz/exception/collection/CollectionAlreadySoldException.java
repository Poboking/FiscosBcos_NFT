package org.knight.app.biz.exception.collection;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/1 19:16
 */
@ExceptionMapper(code = "400", msg = "Collection Already Sold Exception")
public class CollectionAlreadySoldException extends RuntimeException{
    public CollectionAlreadySoldException(String message) {
        super(message);
    }
}
