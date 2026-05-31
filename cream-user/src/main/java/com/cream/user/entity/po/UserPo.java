package com.cream.user.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户 Po
 *
 * @author Cream
 * @since 2026-05-31 13:41
 */
@Data
@TableName("user")
public class UserPo {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    private String name;

    private Integer age;

    @TableField(fill = FieldFill.INSERT)
    private Integer deteled;

    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.UPDATE)
    private String updateBy;

    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)
    private Integer version;
}
