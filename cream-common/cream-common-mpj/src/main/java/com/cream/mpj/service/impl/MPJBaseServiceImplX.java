package com.cream.mpj.service.impl;

import com.cream.mpj.mapper.MPJBaseMapperX;
import com.cream.mpj.service.MPJBaseServiceX;
import com.github.yulichang.base.MPJBaseServiceImpl;

/**
 * MPJBaseServiceImpl 拓展
 *
 * @author Cream
 * @since 2026-06-09 22:59
 */
public class MPJBaseServiceImplX<M extends MPJBaseMapperX<T>, T> extends MPJBaseServiceImpl<M,T> implements MPJBaseServiceX<T> {

}