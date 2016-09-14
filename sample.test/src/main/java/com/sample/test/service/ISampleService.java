package com.sample.test.service;

import com.sample.core.exception.UnifiedException;

/**
 * Created by andongxu on 16-8-17.
 */
public interface ISampleService<I, O> extends IService<I, O> {

    public O service(I i) throws UnifiedException;

}
