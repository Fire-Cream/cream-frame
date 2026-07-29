package com.cream.user.service;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

/**
 * 外部系统触发器
 *
 * @author Cream
 * @since 2026-07-12 23:00
 */
public class CallExternalSystemDelegate implements JavaDelegate {

    /**
     * 触发器
     *
     * @param execution 触发传递参数
     * @author Cream
     * @since 2026-07-12 14:12
     */
    @Override
    public void execute(DelegateExecution execution) {
        System.out.println("你的请假申请，部门领导已同意！");
    }

}