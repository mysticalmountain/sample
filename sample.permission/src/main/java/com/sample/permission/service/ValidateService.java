package com.sample.permission.service;

import com.sample.core.Constant;
import com.sample.core.exception.ExceptionLevel;
import com.sample.core.exception.UnifiedException;
import com.sample.core.service.*;
import com.sample.core.service.handler.ServiceHandlerChain;
import com.sample.permission.dto.BaseRsp;
import com.sample.permission.dto.ValidateReq;
import com.sample.permission.dto.ValidateRsp;
import org.apache.commons.beanutils.BeanUtils;
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
    private ServiceHandlerChain<ValidateReq, ValidateRsp> chain;

    @Override
    public ValidateRsp doService(ValidateReq validateReq) throws UnifiedException {
        Service service = this.getClass().getAnnotation(Service.class);
        return chain.handle(validateReq, service);
    }

    @Override
    public ValidateRsp captureException(ValidateReq validateReq, UnifiedException ue) throws UnifiedException {
        try {
            ValidateRsp validateRsp = new ValidateRsp();
            BeanUtils.copyProperties(validateRsp, validateReq);
            validateRsp.setSuccess(false);
            validateRsp.setErrorCode(ue.getErrorCode());
            validateRsp.setErrorMsg(ue.getErrorMessage());
            return validateRsp;
        } catch (Exception e) {
            throw new UnifiedException(ExceptionLevel.SERIOUS, Constant.EXCEPTION_UNKNOWN[0], Constant.EXCEPTION_UNKNOWN[1], null, null, e);
        }
    }
}
