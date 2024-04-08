package org.knight.app.biz.exception.collection;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/6 21:40
 */
@ExceptionMapper(code = "400", msg = "Member hold collection not found")
public class HoldCollectionNotFoundException extends RuntimeException {
    public HoldCollectionNotFoundException(String message){
        super(message);
    }
}
