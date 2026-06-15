package com.cream.user.entity.vo;

import com.cream.mpj.base.entity.vo.BaseVo;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统用户集合VO实体类
 *
 * @author Cream
 * @since 2026-06-14 23:02:13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "SysUserVo", description = "系统用户")
public class SysUserListVo extends BaseVo {

    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    private String id;

    /**
     * 姓名
     */
    @Schema(description = "姓名")
    private String name;

    /**
     * 年龄
     */
    @Schema(description = "年龄")
    private Integer age;

}
