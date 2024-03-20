package org.sziit.presentation.exception;

import com.feiniaojin.gracefulresponse.api.ExceptionAliasFor;
import org.springframework.web.bind.MethodArgumentNotValidException;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/12 21:07
 */
@ExceptionAliasFor(code = "400", msg = "Bad Request - 参数校验失败", aliasFor = MethodArgumentNotValidException.class)
public class ParameterBodyValidationFailedException extends RuntimeException{
    /*
    * 参数校验失败异常, 但是也没什么用.
     */
    public ParameterBodyValidationFailedException(String message) {
        super(message);
    }
}
