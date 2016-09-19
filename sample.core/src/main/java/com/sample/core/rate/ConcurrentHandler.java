package com.sample.core.rate;

import com.sample.core.Constant;
import com.sample.core.exception.ExceptionLevel;
import com.sample.core.exception.UnifiedException;
import com.sample.core.log.Log;
import com.sample.core.log.Log4jLog;
import com.sample.core.service.aspect.ServiceAspect;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by andongxu on 9/19/16.
 */
@Component
public class ConcurrentHandler implements IConcurrentHandler {

    private Log log = Log4jLog.getLog(this.getClass());

    @Autowired
    private RateConfig rateConfig;

    @Autowired
    private Semaphore semaphore;

    @Bean
    public Semaphore semaphore(RateConfig rateRule) {
        return new Semaphore(rateRule.getPermits());
    }

    @Override
    public Object handle(ServiceAspect serviceAspect, ProceedingJoinPoint joinPoint) throws Throwable {
        if (rateConfig.isOnOff()) {
            try {
                boolean flag = semaphore.tryAcquire(rateConfig.getTimeout(), TimeUnit.valueOf(rateConfig.getTimeUnit()));
                if (flag) {
                    if (semaphore.availablePermits() > 0) {
                        return serviceAspect.doExecute(joinPoint);
                    } else {
                        log.fatal(Constant.EXCEPTION_CONCURRENT[1]);
                        throw new UnifiedException(ExceptionLevel.SERIOUS, Constant.EXCEPTION_CONCURRENT[0], Constant.EXCEPTION_CONCURRENT[1], null, null, null);
                    }
                } else {
                    log.fatal(Constant.EXCEPTION_CONCURRENT[1]);
                    throw new UnifiedException(ExceptionLevel.SERIOUS, Constant.EXCEPTION_CONCURRENT[0], Constant.EXCEPTION_CONCURRENT[1], null, null, null);
                }
            } catch (InterruptedException e) {
                throw e;
            } finally {
                semaphore.release();
            }
        } else {
            return serviceAspect.doExecute(joinPoint);
        }
    }
}
