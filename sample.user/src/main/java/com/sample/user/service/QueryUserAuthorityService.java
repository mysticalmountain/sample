package com.sample.user.service;

import com.sample.core.exception.UnifiedException;
import com.sample.core.model.dto.GenericSetRsp;
import com.sample.core.model.dto.IdLongReq;
import com.sample.core.service.AbstractSampleService;
import com.sample.core.service.Service;
import com.sample.core.service.handler.ServiceHandlerChain;
import com.sample.user.dto.AuthorityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 16-11-23.
 */
@Component
@Service(code = "queryUserAuthority", name = "查询用户凭证")
public class QueryUserAuthorityService extends AbstractSampleService<IdLongReq, GenericSetRsp<AuthorityDto>> {

    @Autowired
    private ServiceHandlerChain<IdLongReq, GenericSetRsp<AuthorityDto>> chain;

    @Override
    public GenericSetRsp<AuthorityDto> doService(IdLongReq idLongReq) throws UnifiedException {
        return chain.handle(idLongReq, this.getClass().getAnnotation(Service.class));
    }

    @Override
    public GenericSetRsp<AuthorityDto> captureException(IdLongReq idLongReq, UnifiedException ue) throws UnifiedException {
        GenericSetRsp genericSetRsp = new GenericSetRsp();
        genericSetRsp.setErrorCode(ue.getErrorCode());
        genericSetRsp.setErrorMsg(ue.getErrorMessage());
        genericSetRsp.setSuccess(false);
        return genericSetRsp;
    }
}
