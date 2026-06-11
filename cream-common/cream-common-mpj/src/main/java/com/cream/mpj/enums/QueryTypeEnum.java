package com.cream.mpj.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 查询类型枚举
 *
 * @author Cream
 * @since 2026-06-09 22:52
 */
@Getter
@AllArgsConstructor
public enum QueryTypeEnum {

    EQ(0),
    NE(1),
    GT(2),
    LT(3),
    LIKE(4),
    LIKE_COMMA(5), //会在查询值两端填充英文逗号
    GTE(6),
    LTE(7),
    ORDER_BY_COLUMN_DESC(8),
    ORDER_BY_COLUMN_ASC(9),
    LEFT_LIKE(10),
    RIGHT_LIKE(11),
    IN(12);

    /**
     * 值
     */
    public final int value;

}
