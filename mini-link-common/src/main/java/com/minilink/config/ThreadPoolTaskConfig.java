package com.minilink.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author: 徐志斌
 * @CreateTime: 2024-12-6  20:06
 * @Description: 自定义线程池
 * @Version: 1.0
 */
@Configuration
public class ThreadPoolTaskConfig {
    @Bean("threadPoolTaskExecutor")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(8);
        executor.setQueueCapacity(1024);
        executor.setMaxPoolSize(32);
        executor.setKeepAliveSeconds(30);
        executor.setThreadNamePrefix("mini-link-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        executor.initialize();
        return executor;
    }
}
