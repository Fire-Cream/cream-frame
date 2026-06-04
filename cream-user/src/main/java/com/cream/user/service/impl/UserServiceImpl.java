package com.cream.user.service.impl;

import com.cream.user.entity.dto.UserDto;
import com.cream.user.entity.po.UserPo;
import com.cream.user.mapper.UserMapper;
import com.cream.user.service.UserService;
import com.cream.web.entity.Result;
import com.cream.web.enums.ResultEnum;
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
    public Result<String> saveOne(UserDto userDto) {
        UserPo userPo = new UserPo();
        BeanUtils.copyProperties(userDto, userPo);
        int insert = userMapper.insert(userPo);
        if (insert > 0) {
            return Result.ok(ResultEnum.SUCCESS.getCode(), "保存成功");
        } else {
            return Result.ok(ResultEnum.ERROR.getCode(), "保存失败");
        }
    }

    @Override
    public Result<String> deleteOne(String id) {
        int delete = userMapper.deleteById(id);
        if (delete > 0) {
            return Result.ok(ResultEnum.SUCCESS.getCode(), "删除成功");
        } else {
            return Result.ok(ResultEnum.ERROR.getCode(), "删除失败");
        }
    }
}
