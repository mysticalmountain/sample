package com.sample.core.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by andongxu on 10/11/16.
 */
@Component
public final class ThreadExecutorPool {

    @Value("${service.handler.executor.pool.size:1}")
    int serviceHandlerExecutorPoolSize;

    @Bean
    ExecutorService serviceHandlerExecutor() {
        return new ThreadPoolExecutor(serviceHandlerExecutorPoolSize,
                serviceHandlerExecutorPoolSize,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(Integer.MAX_VALUE),
                new ThreadPoolExecutor.AbortPolicy());
    }

    @Autowired
    ExecutorService serviceHandlerExecutor;

    public void execute(Runnable obj) {
        serviceHandlerExecutor.execute(obj);
    }
}
