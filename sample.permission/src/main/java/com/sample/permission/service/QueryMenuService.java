package com.sample.permission.service;

import com.sample.core.exception.UnifiedException;
import com.sample.core.model.dto.GenericRsp;
import com.sample.core.model.dto.IdLongReq;
import com.sample.core.service.AbstractSampleService;
import com.sample.core.service.Service;
import com.sample.core.service.handler.ServiceHandlerChain;
import com.sample.permission.dto.MenuDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 16-11-24.
 */
@Component
@Service(code = "queryMenu", name = "查询菜单")
public class QueryMenuService extends AbstractSampleService<IdLongReq, GenericRsp<MenuDto>> {

    @Autowired
    ServiceHandlerChain<IdLongReq, GenericRsp<MenuDto>> chain;

    @Override
    public GenericRsp<MenuDto> doService(IdLongReq req) throws UnifiedException {
        return chain.handle(req, this.getClass().getAnnotation(Service.class));
    }

    @Override
    public GenericRsp<MenuDto> captureException(IdLongReq req, UnifiedException ue) throws UnifiedException {
        GenericRsp genericRsp = new GenericRsp();
        genericRsp.setErrorCode(ue.getErrorCode());
        genericRsp.setErrorMsg(ue.getErrorMessage());
        genericRsp.setSuccess(false);
        return genericRsp;
    }
}
