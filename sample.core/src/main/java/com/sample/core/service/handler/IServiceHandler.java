package com.sample.core.service.handler;

import com.sample.core.exception.UnifiedException;
import com.sample.core.service.IProcessor;
import com.sample.core.service.Support;

/**
 * Created by andongxu on 16-8-17.
 */
public interface IServiceHandler<I, O> extends Support {

    public O execute(I i, ServiceHandlerChain chain) throws UnifiedException;
}
