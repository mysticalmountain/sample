package com.sample.user.service;

import com.sample.core.exception.UnifiedException;
import com.sample.core.model.dto.GenericReq;
import com.sample.core.model.dto.GenericRsp;
import com.sample.core.model.dto.GenericSetRsp;
import com.sample.core.service.AbstractSampleService;
import com.sample.core.service.Service;
import com.sample.core.service.handler.ServiceHandlerChain;
import com.sample.user.dto.ProfileDto;
import com.sample.user.dto.QueryProfileByUserDto;
import com.sample.user.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 16-11-22.
 */
@Component
@Service(code = "queryProfile", name = "查询简介")
public class QueryProfileService extends AbstractSampleService<GenericReq<QueryProfileByUserDto>, GenericSetRsp<ProfileDto>> {

    @Autowired
    private ServiceHandlerChain<GenericReq<ProfileDto>, GenericSetRsp<ProfileDto>> chain;

    @Override
    public GenericSetRsp<ProfileDto> doService(GenericReq genericReq) throws UnifiedException {
        return chain.handle(genericReq, this.getClass().getAnnotation(Service.class));
    }

    @Override
    public GenericSetRsp<ProfileDto> captureException(GenericReq genericReq, UnifiedException ue) throws UnifiedException {
        GenericSetRsp genericSetRsp = new GenericSetRsp();
        genericSetRsp.setErrorCode(ue.getErrorCode());
        genericSetRsp.setErrorMsg(ue.getErrorMessage());
        genericSetRsp.setSuccess(false);
        return genericSetRsp;
    }
}
