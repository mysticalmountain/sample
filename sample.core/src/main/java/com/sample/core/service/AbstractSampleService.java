package com.sample.core.service;

import com.sample.core.exception.UnifiedException;
import com.sample.core.rate.handler.IRateHandler;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by andongxu on 9/12/16.
 */
public abstract class AbstractSampleService<I, O> implements ISampleService<I, O> {

    @Autowired
    private IRateHandler rateHandler;

    public O service(I i) throws UnifiedException {
        rateHandler.handle();
        return this.doService(i);
    }

    public abstract O doService(I i) throws UnifiedException;

    public abstract O captureException(I i, UnifiedException ue) throws UnifiedException;

}
