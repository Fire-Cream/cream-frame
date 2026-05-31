package com.cream.user.controller;

import com.cream.user.entity.dto.UserDto;
import com.cream.user.entity.req.UserReq;
import com.cream.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *
 * @author Cream
 * @since 2026-05-17 14:51
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class TestController {

    private final UserService userService;

    @PostMapping
    public Integer addUser(@RequestBody UserReq userReq){
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userReq,userDto);
        int i = userService.addUser(userDto);
        return i;
    }



}
