package com.sample.core.service.aspect;

import com.sample.core.exception.ExceptionLevel;
import com.sample.core.exception.UnifiedException;
import com.sample.core.exception.api.IExceptionManager;
import com.sample.core.log.Log;
import com.sample.core.log.Log4jLog;
import com.sample.core.log.LogInfo;
import com.sample.core.log.TransLog;
import com.sample.core.log.api.ILogManager;
import com.sample.core.service.Service;
import com.sample.core.validator.FormatException;
import com.sample.core.validator.IValidatorService;
import com.sample.core.validator.Validator;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.StringWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.springframework.core.LocalVariableTableParameterNameDiscoverer;

/**
 * Created by andongxu on 16-7-28.
 */
@Aspect
@Component
public class ServiceAspect {

    @Autowired
    private IExceptionManager exceptionManager;

    @Autowired
    private IValidatorService validatorService;

    @Autowired
    private ILogManager logManager;

    private Log log = Log4jLog.getLog(this.getClass());

    @Pointcut("this(com.sample.core.service.IService)")
    public void pinticut() {
    }


    @Around(value = "pinticut()")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        Service service = null;
        long begin = System.currentTimeMillis();
        Class c = joinPoint.getTarget().getClass();
        Annotation[] as = c.getAnnotations();
        try {
            Method method = getMethod(joinPoint);
            service = joinPoint.getTarget().getClass().getAnnotation(Service.class);
            if (service == null) {
                //TODO 考虑如何处理
            }
            //记录交易请求日志
            if (service.isWriteLog()) {
                LogInfo<Object[]> logInfo = new LogInfo<Object[]>(service.system(), service.module(), service.trans(), LogInfo.Direction.REQ, joinPoint.getArgs());
                logManager.write(logInfo);
            }
            //请求参数验证
            if (service.isValidateReq()) {
                validatorService.service(joinPoint.getArgs()[0]);
            }
            Object rsp = joinPoint.proceed();
            //记录交易响应日志
            if (service.isWriteLog()) {
                LogInfo<Object> logInfo = new LogInfo<Object>(service.system(), service.module(), service.trans(), LogInfo.Direction.RSP, rsp);
                logManager.write(logInfo);
            }
            return rsp;
        } catch (Exception e) {
            UnifiedException ue;
            if (e instanceof UnifiedException) {
                ue = (UnifiedException) e;
                log.error("Unified exception:" + ue.getErrorMessage());
                exceptionManager.publish((UnifiedException) e);
            } else {
                log.error(e.getMessage(), e);
                ue = new UnifiedException(ExceptionLevel.SERIOUS, "999999", "unknown exception", service.module(), null, e);
                exceptionManager.publish(ue);
            }
            Method method = getMethod1(joinPoint, "captureException");
            Object rsp = method.invoke(joinPoint.getTarget(), joinPoint.getArgs()[0], ue);
            //记录交易响应日志
            if (service.isWriteLog()) {
                LogInfo logInfo = new LogInfo(service.system(), service.module(), service.trans(), LogInfo.Direction.RSP, rsp);
                logManager.write(logInfo);
            }
            return rsp;
        }
    }


    private Method getMethod(ProceedingJoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Method[] methods = joinPoint.getTarget().getClass().getMethods();
        Method resultMethod = null;
        for (Method method : methods) {
            //比较方法名称
            if (method.getName().equals(methodName)) {
                Class[] cls = method.getParameterTypes();
                Object[] objs = joinPoint.getArgs();
                boolean flag = true;
                if (objs.length == cls.length) {
                    return method;
                }
            }
        }
        //TODO 考虑是否需要特殊处理
        return null;
    }

    private Method getMethod(ProceedingJoinPoint joinPoint, String methodName) {
        Method[] methods = joinPoint.getTarget().getClass().getMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                return method;
            }
        }
        return null;
    }

    private Method getMethod1(ProceedingJoinPoint joinPoint, String methodName) {
        Method[] methods = joinPoint.getTarget().getClass().getMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                if (method.getModifiers() == 1) {
                    return method;
                }
            }
        }
        return null;
    }

}
