package com.sample.permission.service;

import com.sample.core.exception.UnifiedException;
import com.sample.core.model.dto.GenericReq;
import com.sample.core.model.dto.Rsp;
import com.sample.core.service.AbstractSampleService;
import com.sample.core.service.Service;
import com.sample.core.service.handler.ServiceHandlerChain;
import com.sample.permission.dto.EditRoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 16-11-21.
 */
@Component
@Service(code = "editRole", name = "编辑角色")
public class EditRoleService extends AbstractSampleService<GenericReq<EditRoleDto>, Rsp> {

    @Autowired
    private ServiceHandlerChain<GenericReq<EditRoleDto>, Rsp> chain;

    @Override
    public Rsp doService(GenericReq<EditRoleDto> editRoleDtoGenericReq) throws UnifiedException {
        return chain.handle(editRoleDtoGenericReq, this.getClass().getAnnotation(Service.class));
    }

    @Override
    public Rsp captureException(GenericReq<EditRoleDto> editRoleDtoGenericReq, UnifiedException ue) throws UnifiedException {
        Rsp rsp = new Rsp();
        rsp.setErrorCode(ue.getErrorCode());
        rsp.setErrorMsg(ue.getErrorMessage());
        rsp.setSuccess(false);
        return rsp;
    }
}
