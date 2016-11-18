package com.sample.user.service;

import com.sample.core.exception.UnifiedException;
import com.sample.core.model.dto.GenericReq;
import com.sample.core.model.dto.GenericRsp;
import com.sample.core.service.AbstractSampleService;
import com.sample.core.service.Service;
import com.sample.core.service.handler.ServiceHandlerChain;
import com.sample.user.dto.LoginReqDto;
import com.sample.user.dto.LoginRspDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 16-11-16.
 */
@Component
@Service(code = "1040")
public class LoginService extends AbstractSampleService<GenericReq<LoginReqDto>, GenericRsp<LoginRspDto>> {

    @Autowired
    private ServiceHandlerChain<GenericReq<LoginReqDto>, GenericRsp<LoginRspDto>> chain;


    @Override
    public GenericRsp<LoginRspDto> doService(GenericReq<LoginReqDto> loginDtoGenericReq) throws UnifiedException {
        return chain.handle(loginDtoGenericReq, this.getClass().getAnnotation(Service.class));
    }

    @Override
    public GenericRsp<LoginRspDto> captureException(GenericReq<LoginReqDto> loginDtoGenericReq, UnifiedException ue) throws UnifiedException {
        GenericRsp<LoginRspDto> genericRsp = new GenericRsp<LoginRspDto>();
        genericRsp.setErrorCode(ue.getErrorCode());
        genericRsp.setErrorMsg(ue.getErrorMessage());
        genericRsp.setSuccess(false);
        return genericRsp;
    }
}
