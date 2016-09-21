package com.sample.core.service;

import com.sample.core.exception.UnifiedException;
import com.sample.core.service.handler.BeforeServiceHandlerChain;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by andongxu on 9/12/16.
 */
public abstract class AbstractSampleService<I, O> implements ISampleService<I, O> {

    @Autowired
    private BeforeServiceHandlerChain<I, O> beforeServiceHandlerChain;

    public O service(I i) throws UnifiedException {
        Service service = this.getClass().getAnnotation(Service.class);
        beforeServiceHandlerChain.handle(i, service);
        return this.doService(i);
    }

    public abstract O doService(I i) throws UnifiedException;

    public abstract O captureException(I i, UnifiedException ue) throws UnifiedException;

}
