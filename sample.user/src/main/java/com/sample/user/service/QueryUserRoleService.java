package com.sample.user.service;

import com.sample.core.exception.UnifiedException;
import com.sample.core.model.dto.GenericSetRsp;
import com.sample.core.model.dto.IdLongReq;
import com.sample.core.service.AbstractSampleService;
import com.sample.core.service.Service;
import com.sample.core.service.handler.ServiceHandlerChain;
import com.sample.permission.dto.RoleDto;
import com.sample.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 16-11-23.
 */
@Component
@Service(code = "queryUserRole", name = "查询用户角色")
public class QueryUserRoleService extends AbstractSampleService<IdLongReq, GenericSetRsp<RoleDto>> {

    @Autowired
    private ServiceHandlerChain<IdLongReq, GenericSetRsp<RoleDto>> chain;

    @Override
    public GenericSetRsp<RoleDto> doService(IdLongReq idLongReq) throws UnifiedException {
        return chain.handle(idLongReq, this.getClass().getAnnotation(Service.class));
    }

    @Override
    public GenericSetRsp<RoleDto> captureException(IdLongReq idLongReq, UnifiedException ue) throws UnifiedException {
        GenericSetRsp genericSetRsp = new GenericSetRsp();
        genericSetRsp.setErrorCode(ue.getErrorCode());
        genericSetRsp.setErrorMsg(ue.getErrorMessage());
        genericSetRsp.setSuccess(false);
        return genericSetRsp;
    }
}
