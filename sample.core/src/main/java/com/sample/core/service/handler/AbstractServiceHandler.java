package com.sample.core.service.handler;

import com.sample.core.Constant;
import com.sample.core.exception.ExceptionLevel;
import com.sample.core.exception.UnifiedException;
import com.sample.core.log.Log;
import com.sample.core.log.Log4jLog;
import com.sample.core.service.Service;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by andongxu on 16-8-31.
 */
@Priority
public abstract class AbstractServiceHandler<I, O> implements IServiceHandler<I, O> {

    private Log log = Log4jLog.getLog(this.getClass());

    @Override
    public O execute(I i, IServiceHandlerChain<I, O> chain, Service service) throws UnifiedException {
        if (support(i, service)) {
            long begin = System.currentTimeMillis();
            O o = null;
            try {
                o = this.doHandle(i, service);
            } finally {
                long end = System.currentTimeMillis();
                try {
                    log.info("execute service [" + service.code() + "] request id [" + PropertyUtils.getProperty(i, "reqId") + "]" + "; time millis:" + (end - begin) + " ms");
                } catch (Exception e) {
                    throw new UnifiedException(ExceptionLevel.SERIOUS, Constant.EXCEPTION_UNKNOWN[0], Constant.EXCEPTION_UNKNOWN[1], null, null, e);
                }
            }
            return o;
        } else {
            return chain.handle(i, service);
        }
    }

    public abstract O doHandle(I i, Service service) throws UnifiedException;

}
