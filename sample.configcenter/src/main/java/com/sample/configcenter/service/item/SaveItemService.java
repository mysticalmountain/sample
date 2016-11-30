package com.sample.configcenter.service.item;

import com.sample.configcenter.dto.item.ItemDto;
import com.sample.core.exception.UnifiedException;
import com.sample.core.model.dto.GenericSetReq;
import com.sample.core.model.dto.Rsp;
import com.sample.core.service.AbstractSampleService;
import com.sample.core.service.Service;
import com.sample.core.service.handler.ServiceHandlerChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by andongxu on 16-11-9.
 */
@Component
@Service(code = "saveItem", name = "保存项目属性")
public class SaveItemService extends AbstractSampleService<GenericSetReq<Set<ItemDto>>, Rsp> {

    @Autowired
    private ServiceHandlerChain<GenericSetReq<Set<ItemDto>>, Rsp> chain;

    @Override
    public Rsp doService(GenericSetReq<Set<ItemDto>> itemDtoGenericSetReq) throws UnifiedException {
        return chain.handle(itemDtoGenericSetReq, this.getClass().getAnnotation(Service.class));
    }

    @Override
    public Rsp captureException(GenericSetReq<Set<ItemDto>> itemDtoGenericSetReq, UnifiedException ue) throws UnifiedException {
        Rsp rsp = new Rsp();
        rsp.setErrorCode(ue.getErrorCode());
        rsp.setErrorMsg(ue.getErrorMessage());
        rsp.setSuccess(false);
        return rsp;
    }
}
