package com.cream.user.service;

import com.cream.mpj.entity.PageResult;
import com.cream.user.entity.dto.UserDto;
import com.cream.user.entity.po.UserPo;

/**
 * 用户 Service
 *
 * @author Cream
 * @since 2026-05-31 14:34
 */
public interface UserService {

    int saveOne(UserDto userDto);

    int deleteOne(String id);

    PageResult<UserPo> page(UserDto userDto);
}
