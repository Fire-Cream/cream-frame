package com.cream.mpj.base.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cream.base.ClassNameUtils;
import com.cream.base.MapperUtils;
import com.cream.mpj.base.entity.dto.BaseDto;
import com.cream.mpj.base.entity.dto.BaseQueryDto;
import com.cream.mpj.base.entity.dto.BaseQueryPageDto;
import com.cream.mpj.base.entity.po.BaseEntity;
import com.cream.mpj.base.entity.vo.BaseVo;
import com.cream.mpj.base.mapper.BaseMPJMapper;
import com.cream.mpj.base.model.BaseSort;
import com.cream.mpj.base.service.BaseMPJService;
import com.cream.mpj.builder.QueryWrapperBuilder;
import com.cream.mpj.service.impl.MPJBaseServiceImplX;
import com.cream.mpj.wrapper.JoinWrappersX;
import com.cream.mpj.wrapper.MPJLambdaWrapperX;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * @param <ID>  主键类型
 * @param <T>   实体类
 * @param <D>   Mapper
 * @param <TSD> 保存DTO
 * @param <TUD> 修改DTO
 * @param <TPD> 分页查询DTO
 * @param <TQD> 查询DTO
 * @param <TPV> 分页查询VO
 * @param <TLV> 集合查询VO
 * @param <TV>  查询VO
 * @author Cream
 * @since 2026-06-10 21:52
 */
public abstract class BaseMPJServiceImpl <
        ID extends Serializable,
        T extends BaseEntity<T>,
        D extends BaseMPJMapper<T>,
        TSD extends BaseDto,
        TUD extends BaseDto,
        TPD extends BaseQueryPageDto,
        TQD extends BaseQueryDto,
        TPV extends BaseVo,
        TLV extends BaseVo,
        TV extends BaseVo
        > extends MPJBaseServiceImplX<D, T>
        implements BaseMPJService<ID, T, TSD, TUD, TPD, TQD, TPV, TLV, TV> {

    protected abstract Class<TPV> getPageVoClass();

    protected abstract Class<TLV> getListVoClass();

    protected abstract Class<TV> getVoClass();

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveOne(TSD ts) {
        T entity = MapperUtils.mapping(ts, getEntityClass());
        return getBaseMapper().insert(entity) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveMany(List<TSD> tsList) {
        List<T> entities = MapperUtils.mappingList(tsList, getEntityClass());
        return saveBatch(entities);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeOne(ID id) {
        return getBaseMapper().deleteById(id) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeMany(List<ID> ids) {
        return getBaseMapper().deleteByIds(ids) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateOne(TUD tu) {
        T entity = MapperUtils.mapping(tu, getEntityClass());
        return getBaseMapper().updateById(entity) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateMany(List<TUD> tuList) {
        List<T> entities = MapperUtils.mappingList(tuList, getEntityClass());
        return updateBatchById(entities);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Page<TPV> page(Integer pageNum, Integer pageSize, TPD tp) {
        // 构建查询参数
        String alias = ClassNameUtils.getClassAbbreviation(getEntityClass());
        MPJLambdaWrapperX<T> wrapper = builderBaseQueryWrapper(alias, tp);
        // 查询
        return selectJoinListPage(new Page<>(pageNum, pageSize), getPageVoClass(), wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<TLV> list(TQD tl) {
        // 构建查询参数
        String alias = ClassNameUtils.getClassAbbreviation(getEntityClass());
        MPJLambdaWrapperX<T> wrapper = builderBaseQueryWrapper(alias, tl);
        // 查询
        return selectJoinList(getListVoClass(), wrapper);
    }

    @Override
    public TV detail(ID id) {
        // 构建查询参数
        String alias = ClassNameUtils.getClassAbbreviation(getEntityClass());
        MPJLambdaWrapperX<T> wrapper = JoinWrappersX.lambda(getEntityClass());
        wrapper.setAlias(alias).selectAll();
        // 生成查询条件
        wrapper.eq(getBaseMapper()::selectById, id);
        // 查询
        return selectJoinOne(getVoClass(), wrapper);
    }

    public <P extends BaseQueryDto> MPJLambdaWrapperX<T> builderBaseQueryWrapper(String alias, P p) {
        // 构建查询参数
        MPJLambdaWrapperX<T> wrapper = JoinWrappersX.lambda(getEntityClass());
        wrapper.setAlias(alias).selectAll();
        // 生成查询条件
        QueryWrapperBuilder.build(wrapper, p, alias);
        // 生成排序条件
        if (p.getSorts() != null && !p.getSorts().isEmpty()) {
            for (BaseSort baseSort : p.getSorts()) {
                wrapper.orderBy(baseSort.isValid(), baseSort.getOrder().equalsIgnoreCase("asc"), baseSort.getField());
            }
        }
        return wrapper;
    }

}