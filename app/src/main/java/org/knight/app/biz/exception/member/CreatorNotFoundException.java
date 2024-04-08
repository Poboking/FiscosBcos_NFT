package org.knight.app.biz.exception.member;

import com.feiniaojin.gracefulresponse.api.ExceptionMapper;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/7 10:45
 */
@ExceptionMapper(code = "400", msg = "Creator not found")
public class CreatorNotFoundException extends RuntimeException{
    public CreatorNotFoundException(String message){
        super(message);
    }
}
