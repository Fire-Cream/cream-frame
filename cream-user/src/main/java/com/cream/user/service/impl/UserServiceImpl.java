package com.cream.user.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cream.mpj.entity.PageResult;
import com.cream.user.entity.dto.UserDto;
import com.cream.user.entity.po.UserPo;
import com.cream.user.mapper.UserMapper;
import com.cream.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 用户 ServiceImpl
 *
 * @author Cream
 * @since 2026-05-31 14:39
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Override
    public int saveOne(UserDto userDto) {
        UserPo userPo = new UserPo();
        BeanUtils.copyProperties(userDto, userPo);
        return userMapper.insert(userPo);
    }

    @Override
    public int deleteOne(String id) {
        return userMapper.deleteById(id);
    }

    @Override
    public PageResult<UserPo> page(UserDto userDto) {
        IPage<UserPo> userPoIPage = new Page<>(userDto.getPageIndex(), userDto.getPageSize());
        IPage<UserPo> userPage = userMapper.getUserPage(userPoIPage);
        PageResult<UserPo> userPoPageResult = new PageResult<>();
        userPoPageResult.loadData(userPage);
        return userPoPageResult;
    }
}
