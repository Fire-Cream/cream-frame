package com.cream.user.service;

import com.cream.user.entity.dto.UserDto;

/**
 * 用户 Service
 *
 * @author Cream
 * @since 2026-05-31 14:34
 */
public interface UserService {

    int addUser(UserDto userDto);
}
