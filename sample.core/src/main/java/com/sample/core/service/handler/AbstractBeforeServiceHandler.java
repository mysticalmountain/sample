package com.sample.core.service.handler;

import com.sample.core.Constant;
import com.sample.core.exception.ExceptionLevel;
import com.sample.core.exception.UnifiedException;
import com.sample.core.log.Log;
import com.sample.core.log.Log4jLog;
import com.sample.core.service.Service;
import org.apache.commons.beanutils.PropertyUtils;

/**
 * Created by andongxu on 9/20/16.
 */
@Priority
public abstract class AbstractBeforeServiceHandler<I, O> implements IServiceHandler<I, O> {

    private Log log = Log4jLog.getLog(this.getClass());

    @Override
    public O execute(I i, IServiceHandlerChain<I, O> chain, Service service) throws UnifiedException {
        O o = null;
        if (support(i)) {
            try {
                log.info("execute service [" + service.code() + "] request id [" + PropertyUtils.getProperty(i , "reqId") + "]");
            } catch (Exception e) {
                throw new UnifiedException(ExceptionLevel.SERIOUS, Constant.EXCEPTION_UNKNOWN[0], Constant.EXCEPTION_UNKNOWN[1], null, null, e);
            }
            //TODO 考虑异步处理
            o = this.doHandle(i, service);
            if (o != null) {
                return o;
            }
        }
        o =  chain.handle(i, service);
        if (o != null) {
            return o;
        } else {
            return null;
        }
    }

    public abstract O doHandle(I i, Service service) throws UnifiedException;


}
