package org.knight.app.biz.exception.collection;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/11 20:44
 */
@ExceptionMapper(code = "400", msg = "BadRequest: Collection Already Given")
public class CollectionAlreadyGivenException extends RuntimeException{
    public CollectionAlreadyGivenException(String message) {
        super(message);
    }
}
