package com.sample.permission.service;

import com.sample.core.Constant;
import com.sample.core.exception.ExceptionLevel;
import com.sample.core.exception.UnifiedException;
import com.sample.core.service.AbstractSampleService;
import com.sample.core.service.ISampleService;
import com.sample.core.service.Service;
import com.sample.core.service.handler.ServiceHandlerChain;
import com.sample.permission.dto.BaseRsp;
import com.sample.permission.dto.EditRoleReq;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 9/12/16.
 */
@Component
@com.sample.core.service.Service(code = "1002")
public class EditRoleService extends AbstractSampleService<EditRoleReq, BaseRsp> {

    @Autowired
    private ServiceHandlerChain chain;

    @Override
    public BaseRsp doService(EditRoleReq editRoleReq) throws UnifiedException {
        return null;
    }

    @Override
    public BaseRsp captureException(EditRoleReq editRoleReq, UnifiedException ue) throws UnifiedException {
        try {
            BaseRsp baseRsp = new BaseRsp();
            BeanUtils.copyProperties(baseRsp, editRoleReq);
            baseRsp.setSuccess(false);
            baseRsp.setErrorCode(ue.getErrorCode());
            baseRsp.setErrorMsg(ue.getErrorMessage());
            return baseRsp;
        } catch (Exception e) {
            throw new UnifiedException(ExceptionLevel.SERIOUS, Constant.EXCEPTION_UNKNOWN[0], Constant.EXCEPTION_UNKNOWN[1], null, null, e);
        }
    }
}
