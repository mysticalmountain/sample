package com.sample.core.validator;

import com.sample.core.exception.UnifiedException;
import com.sample.core.model.dto.RspDto;
import com.sample.core.service.IProcessor;

/**
 * Created by andongxu on 16-8-18.
 */
public interface IValidatorService<I, O> {

    public O service(I i) throws UnifiedException;

}
