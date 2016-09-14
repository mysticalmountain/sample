package com.sample.web.dispatch;

import com.sample.core.exception.UnifiedException;
import com.sample.core.service.ISampleService;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 9/11/16.
 */
@Component
public class CommonServiceVisitor<D, O> implements IServiceVisitor<D, O> {
    @Override
    public O visit(ISampleService<D, O> service, IServiceData<D> serviceData) throws UnifiedException {
        D d = serviceData.get();
        return service.service(d);
    }
}
