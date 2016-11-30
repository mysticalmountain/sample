package com.sample.configcenter.service.item;

import com.sample.configcenter.dto.item.ItemDto;
import com.sample.core.exception.UnifiedException;
import com.sample.core.model.dto.GenericReq;
import com.sample.core.model.dto.Rsp;
import com.sample.core.service.AbstractSampleService;
import com.sample.core.service.Service;
import com.sample.core.service.handler.ServiceHandlerChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by andongxu on 16-11-29.
 */
@Component
@Service(code = "editItem", name = "编辑项目属性")
public class EditItemService extends AbstractSampleService<GenericReq<Set<ItemDto>>, Rsp> {

    @Autowired
    private ServiceHandlerChain<GenericReq<Set<ItemDto>>, Rsp> chain;

    @Override
    public Rsp doService(GenericReq<Set<ItemDto>> genericReq) throws UnifiedException {
        return chain.handle(genericReq, this.getClass().getAnnotation(Service.class));
    }

    @Override
    public Rsp captureException(GenericReq<Set<ItemDto>> genericReq, UnifiedException ue) throws UnifiedException {
        Rsp rsp = new Rsp();
        rsp.setErrorCode(ue.getErrorCode());
        rsp.setErrorMsg(ue.getErrorMessage());
        rsp.setSuccess(false);
        return rsp;
    }
}
