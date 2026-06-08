package com.cream.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cream.user.entity.po.UserPo;
import org.springframework.stereotype.Repository;

/**
 * 用户 Mapper
 *
 * @author Cream
 * @since 2026-05-31 13:41
 */
@Repository
public interface UserMapper extends BaseMapper<UserPo> {

    IPage<UserPo> getUserPage(IPage<UserPo> userPoIPage);

}
