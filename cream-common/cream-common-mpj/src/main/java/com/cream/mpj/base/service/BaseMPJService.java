package com.cream.mpj.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cream.mpj.base.entity.dto.BaseDto;
import com.cream.mpj.base.entity.dto.BaseQueryDto;
import com.cream.mpj.base.entity.dto.BaseQueryPageDto;
import com.cream.mpj.base.entity.po.BaseEntity;
import com.cream.mpj.base.entity.vo.BaseVo;
import com.cream.mpj.service.MPJBaseServiceX;

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
 * @since 2026-06-10 21:35
 */
public interface BaseMPJService<
        ID extends Serializable,
        T extends BaseEntity<T>,
        TSD extends BaseDto,
        TUD extends BaseDto,
        TPD extends BaseQueryPageDto,
        TQD extends BaseQueryDto,
        TPV extends BaseVo,
        TLV extends BaseVo,
        TV extends BaseVo
        > extends MPJBaseServiceX<T> {

    /**
     * 新增
     *
     * @param ts 需要新增的DTO
     * @return boolean 返回执行状态
     * @author Cream
     * @since 2026-06-10 21:48
     */
    boolean saveOne(TSD ts);

    /**
     * 批量新增
     *
     * @param tsList 需要新增的DTO集合
     * @return boolean 返回执行状态
     * @author Cream
     * @since 2026-06-10 21:48
     */
    boolean saveMany(List<TSD> tsList);

    /**
     * 删除
     *
     * @param id 需要删除的主键
     * @return boolean 返回执行状态
     * @author Cream
     * @since 2026-06-10 21:48
     */
    boolean removeOne(ID id);

    /**
     * 批量删除
     *
     * @param ids 需要删除的主键集合
     * @return boolean 返回执行状态
     * @author Cream
     * @since 2026-06-10 21:48
     */
    boolean removeMany(List<ID> ids);

    /**
     * 修改
     *
     * @param tu 需要修改的DTO
     * @return boolean 返回执行状态
     * @author Cream
     * @since 2026-06-10 21:48
     */
    boolean updateOne(TUD tu);

    /**
     * 批量修改
     *
     * @param tuList 需要修改的DTO集合
     * @return boolean 返回执行状态
     * @author Cream
     * @since 2026-06-10 21:48
     */
    boolean updateMany(List<TUD> tuList);

    /**
     * 分页查询
     *
     * @param pageNum  页码数
     * @param pageSize 页码大小
     * @param tp       分页查询DTO
     * @return com.baomidou.mybatisplus.core.metadata.IPage<TPV> 查询的分页数据
     * @author Cream
     * @since 2026-06-10 21:48
     */
    IPage<TPV> page(Integer pageNum, Integer pageSize, TPD tp);

    /**
     * 集合查询
     *
     * @param tl 集合查询DTO
     * @return java.util.List<TLV> 查询的集合数据
     * @author Cream
     * @since 2026-06-10 21:48
     */
    List<TLV> list(TQD tl);

    /**
     * 查询
     *
     * @param id 查询ID
     * @return TV 查询的数据
     * @author Cream
     * @since 2026-06-10 21:48
     */
    TV detail(ID id);

}

