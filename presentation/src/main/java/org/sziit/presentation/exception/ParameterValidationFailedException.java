package org.sziit.presentation.exception;

import com.feiniaojin.gracefulresponse.api.ExceptionAliasFor;
import jakarta.validation.ConstraintViolationException;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/12 18:59
 */
@ExceptionAliasFor(code = "400", msg = "Bad Request - 参数校验失败", aliasFor = ConstraintViolationException.class)
public class ParameterValidationFailedException extends RuntimeException{
    /*
    * 参数校验失败异常, 但是也没什么用, Graceful-Response框架把这个异常也给捕获了!!!(╬▔皿▔)╯
    * @ExceptionAliasFor 没用起作用, 导致无法设置hibernate validator参数校验异常信息, 无法返回自定义的异常信息
    * (ノへ￣、)
     */
    public ParameterValidationFailedException(String message) {
        super(message);
    }
}
