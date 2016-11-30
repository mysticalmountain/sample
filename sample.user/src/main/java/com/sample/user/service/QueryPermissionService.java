package com.sample.user.service;

import com.sample.core.exception.UnifiedException;
import com.sample.core.model.dto.GenericReq;
import com.sample.core.model.dto.GenericSetRsp;
import com.sample.core.model.dto.Req;
import com.sample.core.service.AbstractSampleService;
import com.sample.core.service.Service;
import com.sample.core.service.handler.ServiceHandlerChain;
import com.sample.permission.dto.PermissionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 16-11-18.
 */
@Component
@Service(code = "queryPermission", name = "查询权限")
public class QueryPermissionService extends AbstractSampleService<GenericReq<Req>, GenericSetRsp<PermissionDto>> {

    @Autowired
    private ServiceHandlerChain<GenericReq<Req>, GenericSetRsp<PermissionDto>> chain;

    @Override
    public GenericSetRsp<PermissionDto> doService(GenericReq<Req> reqGenericReq) throws UnifiedException {
        return chain.handle(reqGenericReq, this.getClass().getAnnotation(Service.class));
    }

    @Override
    public GenericSetRsp<PermissionDto> captureException(GenericReq<Req> reqGenericReq, UnifiedException ue) throws UnifiedException {
        GenericSetRsp genericRsp = new GenericSetRsp();
        genericRsp.setErrorMsg(ue.getErrorMessage());
        genericRsp.setSuccess(false);
        return genericRsp;
    }
}
