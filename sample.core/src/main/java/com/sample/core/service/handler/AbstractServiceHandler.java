package com.sample.core.service.handler;

import com.sample.core.exception.UnifiedException;
import com.sample.core.service.Parent;

/**
 * Created by andongxu on 16-8-31.
 */
public abstract class AbstractServiceHandler<I, O> implements IServiceHandler<I, O> {

    public O execute(I i, ServiceHandlerChain chain) throws UnifiedException {
        if (support(i)) {
            //TODO 考虑异步处理
            return this.doHandle(i);
        }
        return chain.handle(i);
    }

    @Parent
    public abstract O doHandle(I i) throws UnifiedException;

}
