package com.sample.core.service.handler;

import com.sample.core.exception.UnifiedException;
import com.sample.core.service.Service;

/**
 * Created by andongxu on 9/20/16.
 */
public interface IServiceHandlerChain<I, O> {

    public O handle(I i, Service service) throws UnifiedException;
}
