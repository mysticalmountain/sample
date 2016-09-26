package com.sample.core.service;

import com.sample.core.Constant;
import com.sample.core.exception.ExceptionLevel;
import com.sample.core.exception.UnifiedException;
import com.sample.core.log.Log;
import com.sample.core.log.Log4jLog;
import com.sample.core.service.handler.AfterServiceHandlerChain;
import com.sample.core.service.handler.BeforeServiceHandlerChain;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by andongxu on 9/12/16.
 */
public abstract class AbstractSampleService<I, O> implements ISampleService<I, O> {

    private Log log = Log4jLog.getLog(this.getClass());

    @Autowired
    private BeforeServiceHandlerChain<I, O> beforeServiceHandlerChain;
    @Autowired
    private AfterServiceHandlerChain<O, O> afterServiceHandlerChain;

    public O service(I i) throws UnifiedException {
        Service service = this.getClass().getAnnotation(Service.class);
        try {
            log.info("execute service [" + service.code() + "] request id [" + PropertyUtils.getProperty(i , "reqId") + "]");
        } catch (Exception e) {
            throw new UnifiedException(ExceptionLevel.SERIOUS, Constant.EXCEPTION_UNKNOWN[0], Constant.EXCEPTION_UNKNOWN[1], null, null, e);
        }
        O res = beforeServiceHandlerChain.handle(i, service);
        if (res != null) {
            return res;
        } else {
            O rsp = this.doService(i);
            afterServiceHandlerChain.handle(rsp, service);
            return rsp;
        }
    }

    public abstract O doService(I i) throws UnifiedException;

    public abstract O captureException(I i, UnifiedException ue) throws UnifiedException;

//    public O exceptionHandler(I i, UnifiedException ue) throws UnifiedException {
//        if (Constant.EXCEPTION_REPEAT_REQUEST[0].equals(ue.getErrorCode())) {       //重复请求异常
//            Service service = this.getClass().getAnnotation(Service.class);
//            return idempotentServiceChain.handle(i, service);
//        } else {                                                                    //
//            return this.captureException(i, ue);
//        }
//    }

}
