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

import javax.transaction.Transactional;

/**
 * Created by andongxu on 9/12/16.
 */
public abstract class AbstractSampleService<I, O> implements ISampleService<I, O> {

    private Log log = Log4jLog.getLog(this.getClass());

    @Autowired(required = false)
    private BeforeServiceHandlerChain<I, O> beforeServiceHandlerChain;
    @Autowired(required = false)
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

    @Transactional(value = Transactional.TxType.REQUIRED)
    public abstract O doService(I i) throws UnifiedException;

    public abstract O captureException(I i, UnifiedException ue) throws UnifiedException;

}
