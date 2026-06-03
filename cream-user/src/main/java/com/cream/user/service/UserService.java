package com.cream.user.service;

import com.cream.user.entity.dto.UserDto;
import com.cream.web.entity.Result;

/**
 * 用户 Service
 *
 * @author Cream
 * @since 2026-05-31 14:34
 */
public interface UserService {

    Result<String> addUser(UserDto userDto);
}
