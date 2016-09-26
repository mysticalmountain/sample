package com.sample.core.service.handler;

import com.sample.core.Constant;
import com.sample.core.exception.ExceptionLevel;
import com.sample.core.exception.UnifiedException;
import com.sample.core.log.Log;
import com.sample.core.log.Log4jLog;
import com.sample.core.service.Service;
import org.apache.commons.beanutils.PropertyUtils;
import sun.reflect.generics.tree.VoidDescriptor;

/**
 * Created by andongxu on 9/23/16.
 */
@Priority
public abstract class AbstractAfterVoidServiceHandler <I> implements IServiceHandler<I, Void> {

    private Log log = Log4jLog.getLog(this.getClass());

    @Override
    public Void execute(I i, IServiceHandlerChain<I, Void> chain, Service service) throws UnifiedException {
        if (support(i)) {
            try {
                log.info("execute service [" + service.code() + "] request id [" + PropertyUtils.getProperty(i , "reqId") + "]");
            } catch (Exception e) {
                throw new UnifiedException(ExceptionLevel.SERIOUS, Constant.EXCEPTION_UNKNOWN[0], Constant.EXCEPTION_UNKNOWN[1], null, null, e);
            }
            //TODO 考虑异步处理
            Void res = this.doHandle(i, service);
            if (res != null) {
                return res;
            }
        }else {
            chain.handle(i, service);
        }
        return null;
    }

    public abstract Void doHandle(I i, Service service) throws UnifiedException;

}
