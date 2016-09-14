package com.sample.permission.service;

import com.sample.core.exception.UnifiedException;
import com.sample.core.service.AbstractSampleService;
import com.sample.core.service.ISampleService;
import com.sample.core.service.Service;
import com.sample.core.service.handler.ServiceHandlerChain;
import com.sample.permission.dto.BaseRsp;
import com.sample.permission.dto.EditRoleReq;
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
        BaseRsp baseRsp = new BaseRsp();
        baseRsp.setSuccess(false);
        return baseRsp;
    }
}
