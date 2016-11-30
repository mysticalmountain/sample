package com.sample.permission.service;

import com.sample.core.exception.UnifiedException;
import com.sample.core.model.dto.GenericReq;
import com.sample.core.model.dto.GenericSetRsp;
import com.sample.core.model.dto.Req;
import com.sample.core.service.AbstractSampleService;
import com.sample.core.service.Service;
import com.sample.core.service.handler.ServiceHandlerChain;
import com.sample.permission.dto.QueryRoleDto;
import com.sample.permission.dto.RoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 16-11-21.
 */
@Component
@Service(code = "queryRole", name = "查询角色")
public class QueryRoleService extends AbstractSampleService<GenericReq<QueryRoleDto>, GenericSetRsp<RoleDto>>{

    @Autowired
    private ServiceHandlerChain<Req, GenericSetRsp<RoleDto>> chain;

    @Override
    public GenericSetRsp<RoleDto> doService(GenericReq<QueryRoleDto> req) throws UnifiedException {
        return chain.handle(req, this.getClass().getAnnotation(Service.class));
    }

    @Override
    public GenericSetRsp<RoleDto> captureException(GenericReq<QueryRoleDto> req, UnifiedException ue) throws UnifiedException {
        GenericSetRsp genericSetRsp = new GenericSetRsp();
        genericSetRsp.setErrorCode(ue.getErrorCode());
        genericSetRsp.setErrorMsg(ue.getErrorMessage());
        genericSetRsp.setSuccess(false);
        return genericSetRsp;
    }
}
