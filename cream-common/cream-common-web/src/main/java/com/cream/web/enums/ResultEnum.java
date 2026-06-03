package com.cream.web.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 响应枚举
 *
 * @author Cream
 * @since 2026-06-03 20:17
 */
@Getter
@AllArgsConstructor
public enum ResultEnum {

    SUCCESS(200, "操作成功"),
    NOT_FOUND(404, "资源未找到"),
    ERROR(500, "操作失败");

    private final Integer code;
    private final String message;

}
