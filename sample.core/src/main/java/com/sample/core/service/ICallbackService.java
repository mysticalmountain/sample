package com.sample.core.service;

import com.sample.core.exception.UnifiedException;

/**
 * Created by andongxu on 16-8-17.
 */
public interface ICallbackService<I, O> extends IService<I, O> {

    public O service(I i, IProcessor processor) throws UnifiedException;
}
