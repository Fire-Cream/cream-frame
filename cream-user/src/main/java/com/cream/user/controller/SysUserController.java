package com.cream.user.controller;

import com.cream.user.entity.po.SysUserPo;
import com.cream.user.entity.dto.save.SysUserSaveDto;
import com.cream.user.entity.dto.update.SysUserUpdateDto;
import com.cream.user.entity.dto.query.SysUserQueryPageDto;
import com.cream.user.entity.dto.query.SysUserQueryDto;
import com.cream.user.entity.vo.SysUserPageVo;
import com.cream.user.entity.vo.SysUserListVo;
import com.cream.user.entity.vo.SysUserVo;
import com.cream.user.service.SysUserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cream.web.constant.ResultMessage;
import com.cream.web.entity.Result;
import com.cream.mpj.entity.PageResult;
import io.swagger.annotations.*;
import jakarta.validation.Valid;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import com.cream.mpj.base.controller.BaseMPJController;

/**
 * 系统用户Controller
 *
 * @author Cream
 * @since 2026-06-14 23:02:13
 */
@Api(tags = "系统用户")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sysUser")
public class SysUserController extends BaseMPJController<
        String,
        SysUserPo,
        SysUserSaveDto,
        SysUserUpdateDto,
        SysUserQueryPageDto,
        SysUserQueryDto,
        SysUserPageVo,
        SysUserListVo,
        SysUserVo
        > {

    private final SysUserService sysUserService;

    @Override
    @PostMapping("/save")
    @ApiOperation(value = "保存系统用户")
    public Result<String> saveOne(@RequestBody @Valid SysUserSaveDto ts) {
        return sysUserService.saveOne(ts) ? Result.ok(HttpStatus.OK.value(), ResultMessage.ADD_SUCCESS) : Result.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), ResultMessage.ADD_ERROR);
    }

    @Override
    @PostMapping("/saveBatch")
    @ApiOperation(value = "批量保存系统用户")
    public Result<String> saveMany(@RequestBody @Valid List<SysUserSaveDto> tsList) {
        return sysUserService.saveMany(tsList) ? Result.ok(HttpStatus.OK.value(), ResultMessage.ADD_BATCH_SUCCESS) : Result.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), ResultMessage.ADD_BATCH_ERROR);
    }

    @Override
    @DeleteMapping("/remove")
    @ApiOperation(value = "删除系统用户")
    public Result<String> removeOne(@RequestParam("id") String id) {
        return sysUserService.removeOne(id) ? Result.ok(HttpStatus.OK.value(), ResultMessage.DELETE_SUCCESS) : Result.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), ResultMessage.DELETE_ERROR);
    }

    @Override
    @DeleteMapping("/removeBatch")
    @ApiOperation(value = "批量删除系统用户")
    public Result<String> removeMany(@RequestParam("ids") List<String> ids) {
        return sysUserService.removeMany(ids) ? Result.ok(HttpStatus.OK.value(), ResultMessage.DELETE_BATCH_SUCCESS) : Result.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), ResultMessage.DELETE_BATCH_ERROR);
    }

    @Override
    @PutMapping("/update")
    @ApiOperation(value = "修改系统用户")
    public Result<String> updateOne(@RequestBody @Valid SysUserUpdateDto tu) {
        return sysUserService.updateOne(tu) ? Result.ok(HttpStatus.OK.value(), ResultMessage.UPDATE_SUCCESS) : Result.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), ResultMessage.UPDATE_ERROR);
    }

    @Override
    @PutMapping("/updateBatch")
    @ApiOperation(value = "批量修改系统用户")
    public Result<String> updateMany(@RequestBody @Valid List<SysUserUpdateDto> tuList) {
        return sysUserService.updateMany(tuList) ? Result.ok(HttpStatus.OK.value(), ResultMessage.UPDATE_BATCH_SUCCESS) : Result.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), ResultMessage.UPDATE_BATCH_ERROR);
    }

    @Override
    @GetMapping("/page")
    @ApiOperation(value = "查询系统用户分页")
    public Result<PageResult<SysUserPageVo>> page(SysUserQueryPageDto tq) {
        IPage<SysUserPageVo> vo = sysUserService.page(tq.getPageNum(), tq.getPageSize(), tq);
        return Result.ok(HttpStatus.OK.value(), ResultMessage.QUERY_SUCCESS, new PageResult<>(vo));
    }

    @Override
    @GetMapping("/list")
    @ApiOperation(value = "查询系统用户列表")
    public Result<List<SysUserListVo>> list(SysUserQueryDto tl) {
        return Result.ok(HttpStatus.OK.value(), ResultMessage.QUERY_SUCCESS, baseMPJService.list(tl));
    }

    @Override
    @GetMapping("/info")
    @ApiOperation(value = "查询系统用户明细")
    public Result<SysUserVo> detail(@RequestParam("id") String id) {
        return Result.ok(HttpStatus.OK.value(), ResultMessage.QUERY_SUCCESS, baseMPJService.detail(id));
    }

}
