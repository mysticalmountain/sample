package com.sample.core.service;

import com.sample.core.exception.UnifiedException;
import com.sample.core.validator.Validator;

/**
 * Created by andongxu on 16-8-17.
 */
public interface ISampleService<I, O> extends IService<I, O> {

    public O service(I i) throws UnifiedException;

}
