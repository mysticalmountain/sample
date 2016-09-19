package com.sample.core.rate.handler;

import com.sample.core.exception.UnifiedException;
import com.sample.core.rate.RateException;
import com.sample.core.service.ISampleService;
import com.sample.core.service.aspect.ServiceAspect;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Created by andongxu on 9/19/16.
 */
public interface IConcurrentHandler {

    public Object handle(ServiceAspect serviceAspect, ProceedingJoinPoint joinPoint) throws Throwable;
}
