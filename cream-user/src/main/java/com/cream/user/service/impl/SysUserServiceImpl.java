package com.cream.user.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.cream.user.entity.po.SysUserPo;
import com.cream.user.entity.dto.save.SysUserSaveDto;
import com.cream.user.entity.dto.update.SysUserUpdateDto;
import com.cream.user.entity.dto.query.SysUserQueryPageDto;
import com.cream.user.entity.dto.query.SysUserQueryDto;
import com.cream.user.entity.vo.SysUserPageVo;
import com.cream.user.entity.vo.SysUserListVo;
import com.cream.user.entity.vo.SysUserVo;
import com.cream.user.mapper.SysUserMapper;
import com.cream.user.service.SysUserService;
import com.cream.mpj.base.service.impl.BaseMPJServiceImpl;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统用户ServiceImpl
 *
 * @author Cream
 * @since 2026-06-14 23:02:13
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class SysUserServiceImpl extends BaseMPJServiceImpl<
        String,
        SysUserPo,
        SysUserMapper,
        SysUserSaveDto,
        SysUserUpdateDto,
        SysUserQueryPageDto,
        SysUserQueryDto,
        SysUserPageVo,
        SysUserListVo,
        SysUserVo
        > implements SysUserService {

    @Override
    protected Class<SysUserPageVo> getPageVoClass() {
        return SysUserPageVo.class;
    }

    @Override
    protected Class<SysUserListVo> getListVoClass() {
        return SysUserListVo.class;
    }

    @Override
    protected Class<SysUserVo> getVoClass() {
        return SysUserVo.class;
    }

}
