package com.sample.configcenter.service.evn;

import com.sample.configcenter.dto.env.EnvDto;
import com.sample.core.exception.UnifiedException;
import com.sample.core.model.dto.GenericReq;
import com.sample.core.model.dto.GenericRsp;
import com.sample.core.service.AbstractSampleService;
import com.sample.core.service.Service;
import com.sample.core.service.handler.ServiceHandlerChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 16-11-25.
 */
@Component
@Service(code = "queryEnv", name = "查询环境")
public class QueryEnvService extends AbstractSampleService<GenericReq<EnvDto>, GenericRsp> {

    @Autowired
    private ServiceHandlerChain<GenericReq<EnvDto>, GenericRsp> chain;

    @Override
    public GenericRsp doService(GenericReq<EnvDto> envDtoGenericReq) throws UnifiedException {
        return chain.handle(envDtoGenericReq, this.getClass().getAnnotation(Service.class));
    }

    @Override
    public GenericRsp captureException(GenericReq<EnvDto> envDtoGenericReq, UnifiedException ue) throws UnifiedException {
        GenericRsp genericRsp = new GenericRsp();
        genericRsp.setErrorCode(ue.getErrorCode());
        genericRsp.setErrorMsg(ue.getErrorMessage());
        genericRsp.setSuccess(false);
        return genericRsp;
    }
}
