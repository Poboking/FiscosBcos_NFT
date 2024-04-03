package org.knight.app.biz.exception.member;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/1 18:58
 */
@ExceptionMapper(code = "400", msg = "Receiver Not Found")
public class ReceiverNotFoundException extends RuntimeException{
    public ReceiverNotFoundException(String message) {
        super(message);
    }
}
