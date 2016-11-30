package com.sample.user.service;

import com.sample.core.exception.UnifiedException;
import com.sample.core.model.dto.GenericReq;
import com.sample.core.model.dto.Rsp;
import com.sample.core.service.AbstractSampleService;
import com.sample.core.service.Service;
import com.sample.core.service.handler.ServiceHandlerChain;
import com.sample.user.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 16-11-18.
 */
@Component
@Service(code = "editUser", name = "编辑用户")
public class EditUserService extends AbstractSampleService<GenericReq<UserDto>, Rsp> {

    @Autowired
    private ServiceHandlerChain<GenericReq<UserDto>, Rsp> chain;

    @Override
    public Rsp doService(GenericReq<UserDto> userDtoGenericReq) throws UnifiedException {
        return chain.handle(userDtoGenericReq, this.getClass().getAnnotation(Service.class));
    }

    @Override
    public Rsp captureException(GenericReq<UserDto> userDtoGenericReq, UnifiedException ue) throws UnifiedException {
        Rsp rsp = new Rsp();
        rsp.setErrorMsg(ue.getErrorMessage());
        rsp.setErrorCode(ue.getErrorCode());
        rsp.setSuccess(false);
        return rsp;
    }
}
