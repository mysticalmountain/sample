package com.sample.core.validator;

import com.sample.core.exception.UnifiedException;

/**
 * Created by andongxu on 16-8-18.
 */
public interface IValidatorService<I, O> {

    public O service(I i) throws UnifiedException;

}
