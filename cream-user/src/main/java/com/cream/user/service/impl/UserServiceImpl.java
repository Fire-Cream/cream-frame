package com.cream.user.service.impl;

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
    public int addUser(UserDto userDto) {
        UserPo userPo = new UserPo();
        BeanUtils.copyProperties(userDto, userPo);
        return userMapper.insert(userPo);
    }
}
