package com.sample.permission.service;

import com.sample.core.exception.UnifiedException;
import com.sample.core.service.AbstractSampleService;
import com.sample.core.service.ISampleService;
import com.sample.core.service.Service;
import com.sample.core.service.handler.ServiceHandlerChain;
import com.sample.permission.dto.BaseRsp;
import com.sample.permission.dto.EditChannelReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 9/12/16.
 */
@Component
@com.sample.core.service.Service(code = "1001")
public class EditChannelService extends AbstractSampleService<EditChannelReq, BaseRsp> {

    @Autowired
    private ServiceHandlerChain chain;

    @Override
    public BaseRsp doService(EditChannelReq editChannelReq) throws UnifiedException {
        return chain.handle(editChannelReq);
    }

    @Override
    public BaseRsp captureException(EditChannelReq editChannelReq, UnifiedException ue) throws UnifiedException {
        BaseRsp baseRsp = new BaseRsp();
        baseRsp.setSuccess(false);
        return baseRsp;
    }
}
