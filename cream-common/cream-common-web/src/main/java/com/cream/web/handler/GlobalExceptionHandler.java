package com.cream.web.handler;

import com.cream.web.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常类
 *
 * @author Cream
 * @since 2026-06-03 22:00
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    public Result<String> runtimeException(RuntimeException exception) {
        log.error(exception.getMessage());
        return Result.fail();
    }

    @ExceptionHandler(value = Exception.class)
    public Result<String> exception(Exception exception) {
        log.error(exception.getMessage());
        return Result.fail();
    }

}
