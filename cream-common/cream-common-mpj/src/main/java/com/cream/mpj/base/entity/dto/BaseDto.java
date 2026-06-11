package com.cream.mpj.base.entity.dto;

import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 基础DTO实体类
 *
 * @author Cream
 * @since 2026-06-10 21:39
 */
@Data
public class BaseDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 版本号（乐观锁）
     */
    @Version
    private Integer version;

}
