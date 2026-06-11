package com.cream.mpj.entity;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 分页返回实体
 *
 * @author Cream
 * @since 2026-06-08 22:13
 */
@Data
@NoArgsConstructor
public class PageResult<T> implements Serializable {

    private Long total;

    private Long size;

    private Long current;

    private Long pages;

    private List<T> records = Collections.emptyList();

    public PageResult(IPage<T> records) {
        this.setCurrent(records.getCurrent());
        this.setPages(records.getPages());
        this.setSize(records.getSize());
        this.setTotal(records.getTotal());
        this.setRecords(records.getRecords());
    }
}
