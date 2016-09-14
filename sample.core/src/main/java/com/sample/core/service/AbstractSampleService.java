package com.sample.core.service;

import com.sample.core.exception.UnifiedException;

/**
 * Created by andongxu on 9/12/16.
 */
public abstract class AbstractSampleService<I, O> implements ISampleService<I, O> {

    public O service(I i) throws UnifiedException {
        return this.doService(i);
    }

    public abstract O doService(I i) throws UnifiedException;

    public abstract O captureException(I i, UnifiedException ue) throws UnifiedException;

    public void tmp(){

    }
}
