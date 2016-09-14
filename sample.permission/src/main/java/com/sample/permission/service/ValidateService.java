package com.sample.permission.service;

import com.sample.core.exception.UnifiedException;
import com.sample.core.service.*;
import com.sample.core.service.handler.ServiceHandlerChain;
import com.sample.permission.dto.BaseRsp;
import com.sample.permission.dto.ValidateReq;
import com.sample.permission.dto.ValidateRsp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by andongxu on 16-8-29.
 */
@Component
@com.sample.core.service.Service(code = "1005")
public class ValidateService extends AbstractSampleService<ValidateReq, ValidateRsp> {

    @Autowired
    private ServiceHandlerChain chain;

    @Override
    public ValidateRsp doService(ValidateReq validateReq) throws UnifiedException {
        return chain.handle(validateReq);
    }

    @Override
    public ValidateRsp captureException(ValidateReq validateReq, UnifiedException ue) throws UnifiedException {
        return new ValidateRsp(false);
    }
}
