package com.cream.user.controller;

import com.cream.user.entity.dto.UserDto;
import com.cream.user.entity.req.UserReq;
import com.cream.user.service.UserService;
import com.cream.web.entity.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

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
    public Result<String> saveOne(@RequestBody UserReq userReq){
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userReq,userDto);
        return userService.saveOne(userDto);
    }

    @DeleteMapping("/{id}")
    public Result<String> deleteOne(@PathVariable String id){
        return userService.deleteOne(id);
    }


}
