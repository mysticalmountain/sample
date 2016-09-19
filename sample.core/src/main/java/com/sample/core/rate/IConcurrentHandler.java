package com.sample.core.rate;

import com.sample.core.service.aspect.ServiceAspect;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Created by andongxu on 9/19/16.
 */
public interface IConcurrentHandler {

    public Object handle(ServiceAspect serviceAspect, ProceedingJoinPoint joinPoint) throws Throwable;
}
