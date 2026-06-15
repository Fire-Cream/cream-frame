package com.cream.user.convert;

import com.cream.user.entity.dto.query.SysUserQueryDto;
import com.cream.user.entity.dto.query.SysUserQueryPageDto;
import com.cream.user.entity.dto.save.SysUserSaveDto;
import com.cream.user.entity.dto.update.SysUserUpdateDto;
import com.cream.user.entity.po.SysUserPo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 系统用户类型转换
 *
 * @author Cream
 * @since 2026-06-14 23:02
 */
@Mapper
public interface SysUserConvert {

    SysUserConvert INSTANCE = Mappers.getMapper(SysUserConvert.class);

    SysUserPo convertDtoToSysUserPo(SysUserSaveDto sysUserSaveDto);

    SysUserPo convertDtoToSysUserPo(SysUserUpdateDto sysUserUpdateDto);

    SysUserPo convertDtoToSysUserPo(SysUserQueryPageDto sysUserQueryPageDto);

    SysUserPo convertDtoToSysUserPo(SysUserQueryDto sysUserQueryDto);
}
