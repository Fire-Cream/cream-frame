package com.cream.user.entity.dto.save;

import com.cream.mpj.base.entity.dto.BaseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统用户保存DTO实体类
 *
 * @author Cream
 * @since 2026-06-14 23:02:13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "SysUserSaveDto", description = "系统用户")
public class SysUserSaveDto extends BaseDto {

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
