package org.knight.app.biz.exception.block;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/9 22:21
 */
@ExceptionMapper(code = "500", msg = "ServerError: Gain from-address failed on FISCO BCOS")
public class GainFromAddressException extends RuntimeException{
    public GainFromAddressException(String message) {
        super(message);
    }
}
