package com.jerry.common.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler
{
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(value = MethodArgumentNotValidException.class)
//    public Result handler(MethodArgumentNotValidException e)
//    {
//        BindingResult result = e.getBindingResult();
//        ObjectError objectError = result.getAllErrors().stream().findFirst().get();
//
//        return Result.fail(objectError.getDefaultMessage());
//    }
}
