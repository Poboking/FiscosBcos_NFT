package org.knight.app.biz.exception.storage;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/2 22:59
 */
@ExceptionMapper(code = "400", msg = "Bad Request: Image Type Illegal(Only .jpg .jpeg .png)")
public class ImageTypeIllegalException extends RuntimeException{
    public ImageTypeIllegalException(String message) {
        super(message);
    }
}
