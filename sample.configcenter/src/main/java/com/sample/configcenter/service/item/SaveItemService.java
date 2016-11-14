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

/**
 * Created by andongxu on 16-11-9.
 */
@Component
@Service(code = "1030")
public class SaveItemService extends AbstractSampleService<GenericSetReq<ItemDto>, Rsp> {

    @Autowired
    private ServiceHandlerChain<GenericSetReq<ItemDto>, Rsp> chain;

    @Override
    public Rsp doService(GenericSetReq<ItemDto> itemDtoGenericSetReq) throws UnifiedException {
        return chain.handle(itemDtoGenericSetReq, this.getClass().getAnnotation(Service.class));
    }

    @Override
    public Rsp captureException(GenericSetReq<ItemDto> itemDtoGenericSetReq, UnifiedException ue) throws UnifiedException {
        Rsp rsp = new Rsp();
        rsp.setErrorCode(ue.getErrorCode());
        rsp.setErrorMsg(ue.getErrorMessage());
        rsp.setSuccess(false);
        return rsp;
    }
}
