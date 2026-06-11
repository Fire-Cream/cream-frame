package com.cream.mpj.base.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cream.mpj.base.entity.dto.BaseDto;
import com.cream.mpj.base.entity.dto.BaseQueryDto;
import com.cream.mpj.base.entity.dto.BaseQueryPageDto;
import com.cream.mpj.base.entity.po.BaseEntity;
import com.cream.mpj.base.entity.vo.BaseVo;
import com.cream.mpj.base.service.BaseMPJService;
import com.cream.mpj.entity.PageResult;
import com.cream.web.constant.ResultMessage;
import com.cream.web.entity.Result;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.List;

/**
 * @param <ID>  主键类型
 * @param <T>   实体类
 * @param <TSD> 保存DTO
 * @param <TUD> 修改DTO
 * @param <TPD> 分页查询DTO
 * @param <TQD> 查询DTO
 * @param <TPV> 分页查询VO
 * @param <TLV> 集合查询VO
 * @param <TV>  查询VO
 * @author Cream
 * @since 2026-06-10 22:05
 */
public class BaseMPJController<
        ID extends Serializable,
        T extends BaseEntity<T>,
        TSD extends BaseDto,
        TUD extends BaseDto,
        TPD extends BaseQueryPageDto,
        TQD extends BaseQueryDto,
        TPV extends BaseVo,
        TLV extends BaseVo,
        TV extends BaseVo
        > {

    public BaseMPJService<ID, T, TSD, TUD, TPD, TQD, TPV, TLV, TV> baseMPJService;

    public Result<String> saveOne(TSD ts) {
        return baseMPJService.saveOne(ts) ? Result.ok(HttpStatus.OK.value(), ResultMessage.ADD_SUCCESS) : Result.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), ResultMessage.ADD_ERROR);
    }

    public Result<String> saveMany(List<TSD> tsList) {
        return baseMPJService.saveMany(tsList) ? Result.ok(HttpStatus.OK.value(), ResultMessage.ADD_BATCH_SUCCESS) : Result.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), ResultMessage.ADD_BATCH_ERROR);
    }

    public Result<String> removeOne(ID id) {
        return baseMPJService.removeOne(id) ? Result.ok(HttpStatus.OK.value(), ResultMessage.DELETE_SUCCESS) : Result.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), ResultMessage.DELETE_ERROR);
    }

    public Result<String> removeMany(List<ID> ids) {
        return baseMPJService.removeMany(ids) ? Result.ok(HttpStatus.OK.value(), ResultMessage.DELETE_BATCH_SUCCESS) : Result.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), ResultMessage.DELETE_BATCH_ERROR);
    }

    public Result<String> updateOne(TUD tu) {
        return baseMPJService.updateOne(tu) ? Result.ok(HttpStatus.OK.value(), ResultMessage.UPDATE_SUCCESS) : Result.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), ResultMessage.UPDATE_ERROR);
    }

    public Result<String> updateMany(List<TUD> tuList) {
        return baseMPJService.updateMany(tuList) ? Result.ok(HttpStatus.OK.value(), ResultMessage.UPDATE_BATCH_SUCCESS) : Result.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), ResultMessage.UPDATE_BATCH_ERROR);
    }

    public Result<PageResult<TPV>> page(TPD tp) {
        IPage<TPV> vo = baseMPJService.page(tp.getPageNum(), tp.getPageSize(), tp);
        return Result.ok(HttpStatus.OK.value(), ResultMessage.QUERY_SUCCESS, new PageResult<>(vo));
    }

    public Result<List<TLV>> list(TQD tl) {
        return Result.ok(HttpStatus.OK.value(), ResultMessage.QUERY_SUCCESS, baseMPJService.list(tl));
    }

    public Result<TV> detail(ID id) {
        return Result.ok(HttpStatus.OK.value(), ResultMessage.QUERY_SUCCESS, baseMPJService.detail(id));
    }

}
