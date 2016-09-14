package com.sample.web.dispatch;

import com.sample.core.exception.UnifiedException;
import com.sample.core.service.ISampleService;

/**
 * Created by andongxu on 9/11/16.
 */
public interface IServiceVisitor<D, O> {

    public O visit(ISampleService<D, O> service, IServiceData<D> serviceData) throws UnifiedException;
}
