package org.knight.app.biz.exception.transaction;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/3 20:46
 */
@ExceptionMapper(code = "400", msg = "Error as a result of Order Already Exists")
public class OrderExistsException extends RuntimeException{
    public OrderExistsException(String message) {
        super(message);
    }
}
