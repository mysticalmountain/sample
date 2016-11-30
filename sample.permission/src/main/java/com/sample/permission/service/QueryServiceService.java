package com.sample.permission.service;

import com.sample.core.exception.UnifiedException;
import com.sample.core.model.dto.GenericSetRsp;
import com.sample.core.model.dto.Req;
import com.sample.core.service.AbstractSampleService;
import com.sample.core.service.Service;
import com.sample.core.service.handler.ServiceHandlerChain;
import com.sample.permission.dto.ServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 16-11-21.
 */
@Component
@Service(code = "queryService", name = "查询服务")
public class QueryServiceService extends AbstractSampleService<Req, GenericSetRsp<ServiceDto>> {

    @Autowired
    private ServiceHandlerChain<Req, GenericSetRsp<ServiceDto>> chain;

    @Override
    public GenericSetRsp<ServiceDto> doService(Req req) throws UnifiedException {
        return chain.handle(req, this.getClass().getAnnotation(Service.class));
    }

    @Override
    public GenericSetRsp<ServiceDto> captureException(Req req, UnifiedException ue) throws UnifiedException {
        GenericSetRsp genericSetRsp = new GenericSetRsp();
        genericSetRsp.setErrorCode(ue.getErrorCode());
        genericSetRsp.setErrorMsg(ue.getErrorMessage());
        genericSetRsp.setSuccess(false);
        return genericSetRsp;
    }
}
