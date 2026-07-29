package com.cream.flowable.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Flowable 线程池配置类
 *
 * @author Cream
 * @since 2026-07-11 22:24
 */
@Configuration
public class FlowableThreadPoolConfig {

    @Bean("flowableExecutor")
    public Executor flowableExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //此方法返回可用处理器的虚拟机的最大数量; 不小于1
        int core = Runtime.getRuntime().availableProcessors();
        // 核心线程数（空闲时保持的最小线程数）
        executor.setCorePoolSize(core);
        // 最大线程数（线程池允许的最大线程数）
        executor.setMaxPoolSize(core * 2 + 1);
        // 空闲线程最大存活时间（秒）
        executor.setKeepAliveSeconds(120);
        // 队列容量（核心线程满时，任务排队等待的数量）
        // 如果传入值大于0，底层队列使用的是LinkedBlockingQueue,否则默认使用SynchronousQueue
        executor.setQueueCapacity(120);
        // 线程名称前缀（方便日志排查）
        executor.setThreadNamePrefix("flowable-");
        // 设置拒绝策略，抛出 RejectedExecutionException来拒绝新任务的处理。
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        // 设置拒绝策略，使用主线程
        //executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 设置拒绝策略，直接丢弃掉
        //executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        // 设置拒绝策略，丢弃最早的未处理的任务请求。
        //executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());
        // 等待所有任务完成后再关闭
        executor.setWaitForTasksToCompleteOnShutdown(true);
        // 最大等待时间（秒）
        executor.setAwaitTerminationSeconds(60);
        return executor;
    }
}
