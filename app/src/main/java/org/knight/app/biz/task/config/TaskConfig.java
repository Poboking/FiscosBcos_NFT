package org.knight.app.biz.task.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/4 19:26
 */
@Configuration
@EnableScheduling
@Log4j2
public class TaskConfig {

    @Bean(name = "defaultScheduler")
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(10); // 设置线程池大小
        taskScheduler.setThreadNamePrefix("default-task-thread-"); // 设置线程名称前缀
        taskScheduler.setAwaitTerminationSeconds(60); // 设置等待所有线程执行完的最大超时时间 单位秒
        taskScheduler.setWaitForTasksToCompleteOnShutdown(true); // 是否等待所有任务执行完毕再关闭线程池
        taskScheduler.setRemoveOnCancelPolicy(true); // 是否从队列中移除已被取消的任务
        return taskScheduler;
    }
}
