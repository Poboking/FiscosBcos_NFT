package org.knight.app.biz.exception.collection;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/1 19:15
 */
@ExceptionMapper(code = "503", msg = "Invalid Collection Price Exception")
public class InvalidCollectionPriceException extends RuntimeException{
    public InvalidCollectionPriceException(String message) {
        super(message);
    }
}
