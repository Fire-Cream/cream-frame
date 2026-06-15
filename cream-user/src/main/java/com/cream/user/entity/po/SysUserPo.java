package com.cream.user.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cream.mpj.base.entity.po.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 系统用户PO实体类
 *
 * @author Cream
 * @since 2026-06-14 23:02:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_user")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Schema(name = "SysUser", description = "系统用户")
public class SysUserPo extends BaseEntity<SysUserPo> {

    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 姓名
     */
    @Schema(description = "姓名")
    @TableField(value = "name")
    private String name;

    /**
     * 年龄
     */
    @Schema(description = "年龄")
    @TableField(value = "age")
    private Integer age;

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
