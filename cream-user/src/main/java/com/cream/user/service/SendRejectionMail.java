package com.cream.user.service;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

/**
 * 发送邮件触发器
 *
 * @author Cream
 * @since 2026-07-12 14:11
 */
public class SendRejectionMail implements JavaDelegate {

    /**
     * 触发器
     *
     * @param execution 触发传递参数
     * @author Cream
     * @since 2026-07-12 14:12
     */
    @Override
    public void execute(DelegateExecution execution) {
        System.out.println("不好意思，你的请假申请被拒绝了");
    }

}
