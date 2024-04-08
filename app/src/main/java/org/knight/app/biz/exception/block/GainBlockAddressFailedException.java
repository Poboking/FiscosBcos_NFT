package org.knight.app.biz.exception.block;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/7 11:43
 */
@ExceptionMapper(code = "500", msg = "BlockChainError: Gain block address failed")
public class GainBlockAddressFailedException extends RuntimeException{
    public GainBlockAddressFailedException(String message) {
        super(message);
    }
}
