package com.sample.core.rate;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by andongxu on 9/19/16.
 */
@Component
public class RateHandler implements IRateHandler {

    @Autowired
    private RateConfig rateConfig;

    @Bean
    public RateLimiter rateLimiter() {
        return RateLimiter.create(rateConfig.getPermitsPerSecond(), rateConfig.getWarmupPeriod(), TimeUnit.valueOf(rateConfig.getTimeUnit()));
    }

    @Autowired
    private RateLimiter rateLimiter;


    @Override
    public void handle() {
        if (rateConfig.isOnOff()) {
//            rateLimiter.tryAcquire();
            rateLimiter.acquire();
        }
    }
}
