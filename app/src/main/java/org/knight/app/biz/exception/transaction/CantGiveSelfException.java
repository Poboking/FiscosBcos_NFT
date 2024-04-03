package org.knight.app.biz.exception.transaction;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/3 22:35
 */
@ExceptionMapper(code = "503", msg = "Can`t Give Yourself Exception")
public class CantGiveSelfException extends RuntimeException{
    public CantGiveSelfException(String message) {
        super(message);
    }
}
