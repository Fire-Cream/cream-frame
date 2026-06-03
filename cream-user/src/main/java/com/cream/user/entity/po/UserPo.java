package com.cream.user.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import com.cream.mpj.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户 Po
 *
 * @author Cream
 * @since 2026-05-31 13:41
 */
@Data
@TableName("user")
@EqualsAndHashCode(callSuper = true)
public class UserPo extends BaseEntity {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    private String name;

    private Integer age;
}
