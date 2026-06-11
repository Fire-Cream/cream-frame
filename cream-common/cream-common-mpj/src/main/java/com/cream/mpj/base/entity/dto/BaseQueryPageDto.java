package com.cream.mpj.base.entity.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 基础分页查询DTO实体类
 *
 * @author Cream
 * @since 2026-06-10 21:41
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseQueryPageDto extends BaseQueryDto{

    /**
     * 页码
     */
    private Integer pageNum = 1;

    /**
     * 每页条数
     */
    private Integer pageSize = 10;

}
