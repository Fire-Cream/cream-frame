package com.cream.web.entity;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * 全局响应类
 *
 * @author Cream
 * @since 2026-06-03 20:20
 */
@Data
public class Result<T> implements Serializable {

    private Boolean success;

    private Integer code;

    private String message;

    private T data;

    private Result() {
    }

    /**
     * 响应成功（不含数据）
     *
     * @return com.cream.web.entity.Result<java.lang.String>
     * @author Cream
     * @since 2026-06-03 21:24
     */
    public static Result<String> ok() {
        Result<String> result = new Result<>();
        result.setSuccess(true);
        result.setCode(HttpStatus.OK.value());
        result.setMessage(HttpStatus.OK.getReasonPhrase());
        return result;
    }

    /**
     * 响应成功（包含数据）
     *
     * @param data 需要返回的数据
     * @return com.cream.web.entity.Result<T>
     * @author Cream
     * @since 2026-06-03 21:24
     */
    public static <T> Result<T> ok(T data) {
        Result<T> result = new Result<>();
        result.setSuccess(true);
        result.setCode(HttpStatus.OK.value());
        result.setMessage(HttpStatus.OK.getReasonPhrase());
        result.setData(data);
        return result;
    }

    /**
     * 响应成功（不含数据）
     *
     * @param code    响应码
     * @param message 响应消息
     * @return com.cream.web.entity.Result<java.lang.String>
     * @author Cream
     * @since 2026-06-03 21:24
     */
    public static Result<String> ok(Integer code, String message) {
        Result<String> result = new Result<>();
        result.setSuccess(true);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    /**
     * 响应成功（不含数据）
     *
     * @param httpStatus 响应枚举
     * @return com.cream.web.entity.Result<java.lang.String>
     * @author Cream
     * @since 2026-06-03 21:24
     */
    public static Result<String> ok(HttpStatus httpStatus) {
        return ok(httpStatus.value(), httpStatus.getReasonPhrase());
    }

    /**
     * 响应成功（包含数据）
     *
     * @param code    响应码
     * @param message 响应消息
     * @param data    需要返回的数据
     * @return com.cream.web.entity.Result<T>
     * @author Cream
     * @since 2026-06-03 21:24
     */
    public static <T> Result<T> ok(Integer code, String message, T data) {
        Result<T> result = new Result<>();
        result.setSuccess(true);
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    /**
     * 响应成功（包含数据）
     *
     * @param httpStatus 响应枚举
     * @param data       需要返回的数据
     * @return com.cream.web.entity.Result<T>
     * @author Cream
     * @since 2026-06-03 21:24
     */
    public static <T> Result<T> ok(HttpStatus httpStatus, T data) {
        return ok(httpStatus.value(), httpStatus.getReasonPhrase(), data);
    }

    /**
     * 响应失败（不含数据）
     *
     * @return com.cream.web.entity.Result<java.lang.String>
     * @author Cream
     * @since 2026-06-03 21:24
     */
    public static Result<String> fail() {
        Result<String> result = new Result<>();
        result.setSuccess(false);
        result.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        result.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        return result;
    }

    /**
     * 响应失败（包含数据）
     *
     * @param data 需要返回的数据
     * @return com.cream.web.entity.Result<T>
     * @author Cream
     * @since 2026-06-03 21:24
     */
    public static <T> Result<T> fail(T data) {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        result.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        result.setData(data);
        return result;
    }

    /**
     * 响应失败（不含数据）
     *
     * @param code    响应码
     * @param message 响应消息
     * @return com.cream.web.entity.Result<java.lang.String>
     * @author Cream
     * @since 2026-06-03 21:24
     */
    public static Result<String> fail(Integer code, String message) {
        Result<String> result = new Result<>();
        result.setSuccess(false);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    /**
     * 响应失败（不含数据）
     *
     * @param httpStatus 响应枚举
     * @return com.cream.web.entity.Result<java.lang.String>
     * @author Cream
     * @since 2026-06-03 21:24
     */
    public static Result<String> fail(HttpStatus httpStatus) {
        return ok(httpStatus.value(), httpStatus.getReasonPhrase());
    }

    /**
     * 响应失败（包含数据）
     *
     * @param code    响应码
     * @param message 响应消息
     * @param data    需要返回的数据
     * @return com.cream.web.entity.Result<T>
     * @author Cream
     * @since 2026-06-03 21:24
     */
    public static <T> Result<T> fail(Integer code, String message, T data) {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    /**
     * 响应失败（包含数据）
     *
     * @param httpStatus 响应枚举
     * @param data       需要返回的数据
     * @return com.cream.web.entity.Result<T>
     * @author Cream
     * @since 2026-06-03 21:24
     */
    public static <T> Result<T> fail(HttpStatus httpStatus, T data) {
        return fail(httpStatus.value(), httpStatus.getReasonPhrase(), data);
    }

}
