package org.knight.app.biz.exception.transaction;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/12 16:58
 */
@ExceptionMapper(code = "400" ,msg = "BadRequest: Resale Collection Not Found")
public class ResaleCollectionNotFoundException extends RuntimeException{
    public ResaleCollectionNotFoundException(String message) {
        super(message);
    }
}
