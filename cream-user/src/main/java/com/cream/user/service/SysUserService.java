package com.cream.user.service;

import com.cream.user.entity.po.SysUserPo;
import com.cream.user.entity.dto.save.SysUserSaveDto;
import com.cream.user.entity.dto.update.SysUserUpdateDto;
import com.cream.user.entity.dto.query.SysUserQueryPageDto;
import com.cream.user.entity.dto.query.SysUserQueryDto;
import com.cream.user.entity.vo.SysUserPageVo;
import com.cream.user.entity.vo.SysUserListVo;
import com.cream.user.entity.vo.SysUserVo;
import com.cream.mpj.base.service.BaseMPJService;

/**
 * 系统用户Service
 *
 * @author Cream
 * @since 2026-06-14 23:02:13
 */
public interface SysUserService extends BaseMPJService<
        String,
        SysUserPo,
        SysUserSaveDto,
        SysUserUpdateDto,
        SysUserQueryPageDto,
        SysUserQueryDto,
        SysUserPageVo,
        SysUserListVo,
        SysUserVo
        > {

}
