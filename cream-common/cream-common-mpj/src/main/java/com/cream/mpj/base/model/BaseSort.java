package com.cream.mpj.base.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 基础排序实体类
 *
 * @author Cream
 * @since 2026-06-10 21:45
 */
@Data
public class BaseSort implements Serializable {

    /**
     * @since 排序字段名-对应实体类的属性名
     */
    private String field;

    /**
     * @since 排序方向：asc（升序）、desc（降序）
     */
    private String order;

    /**
     * @since 校验排序参数是否有效
     */
    public boolean isValid() {
        // 基本校验：字段名不为空，排序方向为asc或desc
        return field != null && !field.isEmpty()
                && ("asc".equalsIgnoreCase(order) || "desc".equalsIgnoreCase(order));
    }

}