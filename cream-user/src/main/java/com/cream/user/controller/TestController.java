package com.cream.user.controller;

import com.cream.mpj.entity.PageResult;
import com.cream.user.entity.dto.UserDto;
import com.cream.user.entity.po.UserPo;
import com.cream.user.entity.req.UserPageReq;
import com.cream.user.entity.req.UserReq;
import com.cream.user.service.UserService;
import com.cream.web.entity.Result;
import com.cream.web.enums.ResultEnum;
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
    public Result<String> saveOne(@RequestBody UserReq userReq) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userReq, userDto);
        int save = userService.saveOne(userDto);
        if (save > 0) {
            return Result.ok(ResultEnum.SUCCESS.getCode(), "保存成功");
        } else {
            return Result.ok(ResultEnum.ERROR.getCode(), "保存失败");
        }
    }

    @DeleteMapping("/{id}")
    public Result<String> deleteOne(@PathVariable String id) {
        int delete = userService.deleteOne(id);
        if (delete > 0) {
            return Result.ok(ResultEnum.SUCCESS.getCode(), "删除成功");
        } else {
            return Result.ok(ResultEnum.ERROR.getCode(), "删除失败");
        }
    }

    @GetMapping
    public Result<PageResult<UserPo>> findAll(UserPageReq userPageReq) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userPageReq, userDto);
        PageResult<UserPo> page = userService.page(userDto);
        return Result.ok(page);
    }

}
