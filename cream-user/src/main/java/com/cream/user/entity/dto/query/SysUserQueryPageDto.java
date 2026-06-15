package com.cream.user.entity.dto.query;

import com.cream.mpj.base.entity.dto.BaseQueryPageDto;
import com.cream.mpj.annotation.QueryType;
import com.cream.mpj.enums.QueryTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统用户分页查询DTO实体类
 *
 * @author Cream
 * @since 2026-06-14 23:02:13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "SysUserQueryPageDto", description = "系统用户")
public class SysUserQueryPageDto extends BaseQueryPageDto {

    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    @QueryType(filedName = "id", value = QueryTypeEnum.EQ)
    private String id;

    /**
     * 姓名
     */
    @Schema(description = "姓名")
    @QueryType(filedName = "name", value = QueryTypeEnum.EQ)
    private String name;

    /**
     * 年龄
     */
    @Schema(description = "年龄")
    @QueryType(filedName = "age", value = QueryTypeEnum.EQ)
    private Integer age;

}
