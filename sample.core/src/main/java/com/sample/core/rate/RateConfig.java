package com.sample.core.rate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by andongxu on 9/19/16.
 */
@Component
public class RateConfig {

    private String timeUnit = TimeUnit.MILLISECONDS.toString();

    /**
     * 流量控制开关
     */
    @Value("${rate.switch:false}")
    private boolean onOff;
    /**
     * 速率，每秒限制数
     */
    @Value("${rate.permits.per.second:2147483647}")
    private double permitsPerSecond;

    /**
     * 预热时间（毫秒）
     */
    @Value("${rate.permits.warmup:60000}")
    private long warmupPeriod;
    /**
     * 并发限制数据
     */
    @Value("${rate.permits.concurrent:2147483647}")
    private int permits;
    /**
     * 并发获取执行对象超时时间
     */
    @Value("${rate.timeout:0}")
    private long timeout;

    public String getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(String timeUnit) {
        this.timeUnit = timeUnit;
    }

    public double getPermitsPerSecond() {
        return permitsPerSecond;
    }

    public void setPermitsPerSecond(double permitsPerSecond) {
        this.permitsPerSecond = permitsPerSecond;
    }

    public long getWarmupPeriod() {
        return warmupPeriod;
    }

    public void setWarmupPeriod(long warmupPeriod) {
        this.warmupPeriod = warmupPeriod;
    }

    public int getPermits() {
        return permits;
    }

    public void setPermits(int permits) {
        this.permits = permits;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public boolean isOnOff() {
        return onOff;
    }

    public void setOnOff(boolean onOff) {
        this.onOff = onOff;
    }
}
