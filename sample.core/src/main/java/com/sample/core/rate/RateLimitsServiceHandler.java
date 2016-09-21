package com.sample.core.rate;

import com.google.common.util.concurrent.RateLimiter;
import com.sample.core.exception.UnifiedException;
import com.sample.core.service.handler.AbstractBeforeServiceHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by andongxu on 9/20/16.
 */
@Component
public class RateLimitsServiceHandler<I, O> extends AbstractBeforeServiceHandler<I, O> {

    @Autowired
    private RateConfig rateConfig;

    @Bean
    public RateLimiter rateLimiter() {
        return RateLimiter.create(rateConfig.getPermitsPerSecond(), rateConfig.getWarmupPeriod(), TimeUnit.valueOf(rateConfig.getTimeUnit()));
    }

    @Autowired
    private RateLimiter rateLimiter;

    @Override
    public O doHandle(I i, com.sample.core.service.Service service) throws UnifiedException {
        if (rateConfig.isOnOff()) {
            rateLimiter.acquire();
        }
        return null;
    }

    @Override
    public boolean support(Object o) {
        return true;
    }
}
