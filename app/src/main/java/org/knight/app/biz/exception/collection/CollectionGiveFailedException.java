package org.knight.app.biz.exception.collection;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/1 19:06
 */
@ExceptionMapper(code = "503", msg = "Collection Give Failed")
public class CollectionGiveFailedException extends RuntimeException{

    public CollectionGiveFailedException(String message) {
        super(message);
    }
}
