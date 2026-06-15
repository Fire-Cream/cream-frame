package com.cream.user.entity.dto.update;

import com.cream.mpj.base.entity.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统用户修改DTO实体类
 *
 * @author Cream
 * @since 2026-06-14 23:02:13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SysUserUpdateDto对象", description = "系统用户")
public class SysUserUpdateDto extends BaseDto {

    /**
     * 主键ID
     */
    @ApiModelProperty("主键ID")
    private String id;

    /**
     * 姓名
     */
    @ApiModelProperty("姓名")
    private String name;

    /**
     * 年龄
     */
    @ApiModelProperty("年龄")
    private Integer age;

}
