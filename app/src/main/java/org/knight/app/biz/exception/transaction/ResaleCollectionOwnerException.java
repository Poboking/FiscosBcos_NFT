package org.knight.app.biz.exception.transaction;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/14 0:38
 */
@ExceptionMapper(code = "400", msg = "BadRequest: You can't buy your own collection for resale.")
public class ResaleCollectionOwnerException extends RuntimeException {
    public ResaleCollectionOwnerException(String message) {
        super(message);
    }
}
