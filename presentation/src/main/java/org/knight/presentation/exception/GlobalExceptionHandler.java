package org.knight.presentation.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Project:a20-nft-3_6
 * Author:poboking
 * Date:2024/3/7 9:30
 */
@ControllerAdvice
// @RestControllerAdvice 注解用于处理Restfull接口的异常, 而@ControllerAdvice用于处理传统MVC接口的异常
public class GlobalExceptionHandler {

//    @ExceptionHandler(value = Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public Result handleException(HttpServletRequest httpServletRequest, Exception e) {
//        return new Result(StatusCode.FAILED.getCode(), StatusCode.FAILED.getMessage(), null);
//    }
//
//    @ExceptionHandler(value = ApiException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public Result handleBusinessException(HttpServletRequest httpServletRequest, ApiException e) {
//        return new Result(e.getCode(), e.getMessage(), null);
//    }


    /**
     * Hibernate Validator参数校验异常捕获
     * 但是没什么用 Graceful-Response框架把这个异常也给捕获了!!!(￣_￣|||)
     */
    //处理请求参数格式错误 @RequestBody上validate失败后抛出的异常是MethodArgumentNotValidException异常。
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public void handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) throws ParameterValidationFailedException {
        BindingResult bindingResult = ex.getBindingResult();
        StringBuilder sb = new StringBuilder("校验失败:");
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            sb.append(fieldError.getField()).append("：").append(fieldError.getDefaultMessage()).append(", ");
        }
        String msg = sb.toString();
        System.err.println(msg);
        throw new ParameterValidationFailedException(msg);
    }

    //处理请求参数格式错误 @RequestParam上validate失败后抛出的异常是javax.validation.ConstraintViolationException
    @ExceptionHandler(ConstraintViolationException.class)
    public void handleConstraintViolationException(ConstraintViolationException ex) throws ParameterValidationFailedException {
        System.err.println(ex.getMessage());
        throw new ParameterValidationFailedException(ex.getMessage());
    }
}
