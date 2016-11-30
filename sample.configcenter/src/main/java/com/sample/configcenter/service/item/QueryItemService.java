package com.sample.configcenter.service.item;

import com.sample.configcenter.dto.item.ItemDto;
import com.sample.core.exception.UnifiedException;
import com.sample.core.model.dto.GenericReq;
import com.sample.core.model.dto.GenericSetRsp;
import com.sample.core.service.AbstractSampleService;
import com.sample.core.service.Service;
import com.sample.core.service.handler.ServiceHandlerChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 16-11-11.
 */
@Component
@Service(code = "queryItem", name = "查询项目属性")
public class QueryItemService extends AbstractSampleService<GenericReq<ItemDto>, GenericSetRsp<ItemDto>> {

    @Autowired
    private ServiceHandlerChain <GenericReq<ItemDto>, GenericSetRsp<ItemDto>> chain;

    @Override
    public GenericSetRsp<ItemDto> doService(GenericReq<ItemDto> genericReq) throws UnifiedException {
        return chain.handle(genericReq, this.getClass().getAnnotation(Service.class));
    }

    @Override
    public GenericSetRsp<ItemDto> captureException(GenericReq<ItemDto> itemDtoGenericReq, UnifiedException ue) throws UnifiedException {
        GenericSetRsp genericSetRsp = new GenericSetRsp();
        genericSetRsp.setErrorCode(ue.getErrorCode());
        genericSetRsp.setErrorMsg(ue.getErrorMessage());
        genericSetRsp.setSuccess(false);
        return genericSetRsp;
    }
}
