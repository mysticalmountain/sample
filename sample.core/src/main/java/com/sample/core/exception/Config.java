package com.sample.core.exception;

import com.sample.core.exception.handler.ExceptionHandlerChain;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 16-8-18.
 */
@Component
public class Config {

    @Bean(name = "taskExecutor")
    public TaskExecutor taskExecutor() {
        return new ThreadPoolTaskScheduler();
    }

}
