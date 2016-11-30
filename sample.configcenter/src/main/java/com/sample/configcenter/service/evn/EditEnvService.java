package com.sample.configcenter.service.evn;

import com.sample.configcenter.dto.env.EnvDto;
import com.sample.core.exception.UnifiedException;
import com.sample.core.model.dto.GenericReq;
import com.sample.core.model.dto.Rsp;
import com.sample.core.service.AbstractSampleService;
import com.sample.core.service.Service;
import com.sample.core.service.handler.ServiceHandlerChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 16-11-25.
 */
@Component
@Service(code = "editEnv", name = "编辑环境")
public class EditEnvService extends AbstractSampleService<GenericReq<EnvDto>, Rsp> {

    @Autowired
    private ServiceHandlerChain<GenericReq<EnvDto>, Rsp> chain;

    @Override
    public Rsp doService(GenericReq<EnvDto> envDtoGenericReq) throws UnifiedException {
        return chain.handle(envDtoGenericReq, this.getClass().getAnnotation(Service.class));
    }

    @Override
    public Rsp captureException(GenericReq<EnvDto> envDtoGenericReq, UnifiedException ue) throws UnifiedException {
        Rsp rsp = new Rsp();
        rsp.setErrorCode(ue.getErrorCode());
        rsp.setErrorMsg(ue.getErrorMessage());
        rsp.setSuccess(false);
        return rsp;
    }
}
