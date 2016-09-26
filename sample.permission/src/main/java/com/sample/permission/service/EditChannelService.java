package com.sample.permission.service;

import com.sample.core.Constant;
import com.sample.core.exception.ExceptionLevel;
import com.sample.core.exception.UnifiedException;
import com.sample.core.service.AbstractSampleService;
import com.sample.core.service.ISampleService;
import com.sample.core.service.Service;
import com.sample.core.service.handler.ServiceHandlerChain;
import com.sample.permission.dto.BaseRsp;
import com.sample.permission.dto.EditChannelReq;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by andongxu on 9/12/16.
 */
@Component
@com.sample.core.service.Service(code = "1001", isValidateReq = true, isIdempotent = true)
public class EditChannelService extends AbstractSampleService<EditChannelReq, BaseRsp> {

    @Autowired
    private ServiceHandlerChain<EditChannelReq, BaseRsp> chain;

    @Override
    public BaseRsp doService(EditChannelReq editChannelReq) throws UnifiedException {
        return chain.handle(editChannelReq, this.getClass().getAnnotation(Service.class));
    }

    @Override
    public BaseRsp captureException(EditChannelReq editChannelReq, UnifiedException ue) throws UnifiedException {
        try {
            BaseRsp baseRsp = new BaseRsp();
            BeanUtils.copyProperties(baseRsp, editChannelReq);
            baseRsp.setSuccess(false);
            baseRsp.setErrorCode(ue.getErrorCode());
            baseRsp.setErrorMsg(ue.getErrorMessage());
            return baseRsp;
        } catch (Exception e) {
            throw new UnifiedException(ExceptionLevel.SERIOUS, Constant.EXCEPTION_UNKNOWN[0], Constant.EXCEPTION_UNKNOWN[1], null, null, e);
        }
    }
}
