package com.sample.core.service.handler;

import com.sample.core.exception.UnifiedException;
import com.sample.core.service.Parent;
import com.sample.core.service.Service;

/**
 * Created by andongxu on 9/20/16.
 */
public abstract class AbstractBeforeServiceHandler<I, O> implements IServiceHandler<I, O> {

    @Override
    public O execute(I i, IServiceHandlerChain<I, O> chain, Service service) throws UnifiedException {
        if (support(i)) {
            //TODO 考虑异步处理
            return this.doHandle(i, service);
        }else {
            return chain.handle(i, service);
        }
    }

    public abstract O doHandle(I i, Service service) throws UnifiedException;


}
