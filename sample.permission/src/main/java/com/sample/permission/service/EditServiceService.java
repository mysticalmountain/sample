package com.sample.permission.service;

import com.sample.core.exception.UnifiedException;
import com.sample.core.service.*;
import com.sample.core.service.handler.ServiceHandlerChain;
import com.sample.permission.dto.BaseRsp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 16-8-30.
 */
@Component
@com.sample.core.service.Service(code = "1003")
public class EditServiceService<I> extends AbstractSampleService<I, BaseRsp> {

    @Autowired
    private ServiceHandlerChain chain;

    @Override
    public BaseRsp doService(I addReq) throws UnifiedException {
        return chain.handle(addReq);
    }

    @Override
    public BaseRsp captureException(I addReq, UnifiedException ue) throws UnifiedException {
        BaseRsp baseRsp = new BaseRsp();
        baseRsp.setSuccess(false);
        return baseRsp;
    }
}
