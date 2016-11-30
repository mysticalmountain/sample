package com.sample.user.service;

import com.sample.core.exception.UnifiedException;
import com.sample.core.model.dto.GenericReq;
import com.sample.core.model.dto.GenericSetRsp;
import com.sample.core.service.AbstractSampleService;
import com.sample.core.service.Service;
import com.sample.core.service.handler.ServiceHandlerChain;
import com.sample.user.dto.QueryUserDto;
import com.sample.user.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 16-11-18.
 */
@Component
@Service(code = "queryUser", name = "查询用户")
public class QueryUserService extends AbstractSampleService<GenericReq<QueryUserDto>, GenericSetRsp<UserDto>> {

    @Autowired
    private ServiceHandlerChain<GenericReq<QueryUserDto>, GenericSetRsp<UserDto>> chain;

    @Override
    public GenericSetRsp<UserDto> doService(GenericReq<QueryUserDto> queryUserReqDtoGenericReq) throws UnifiedException {
        return chain.handle(queryUserReqDtoGenericReq, this.getClass().getAnnotation(Service.class));
    }

    @Override
    public GenericSetRsp<UserDto> captureException(GenericReq<QueryUserDto> queryUserReqDtoGenericReq, UnifiedException ue) throws UnifiedException {
        GenericSetRsp genericRsp = new GenericSetRsp();
        genericRsp.setErrorMsg(ue.getErrorMessage());
        genericRsp.setSuccess(false);
        return genericRsp;
    }
}
