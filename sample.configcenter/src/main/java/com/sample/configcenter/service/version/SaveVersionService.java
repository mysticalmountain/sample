package com.sample.configcenter.service.version;

import com.sample.configcenter.dto.version.VersionDto;
import com.sample.core.exception.UnifiedException;
import com.sample.core.model.dto.GenericReq;
import com.sample.core.model.dto.Rsp;
import com.sample.core.service.AbstractSampleService;
import com.sample.core.service.Service;
import com.sample.core.service.handler.ServiceHandlerChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 16-11-10.
 */
@Component
@Service(code = "saveVersion", name = "保存项目版本号")
public class SaveVersionService extends AbstractSampleService<GenericReq<VersionDto>, Rsp> {

    @Autowired
    private ServiceHandlerChain<GenericReq<VersionDto>, Rsp> chain;

    @Override
    public Rsp doService(GenericReq<VersionDto> versionDtoGenericReq) throws UnifiedException {
        return chain.handle(versionDtoGenericReq, this.getClass().getAnnotation(Service.class));
    }

    @Override
    public Rsp captureException(GenericReq<VersionDto> versionDtoGenericReq, UnifiedException ue) throws UnifiedException {
        Rsp rsp = new Rsp();
        rsp.setErrorCode(ue.getErrorCode());
        rsp.setErrorMsg(ue.getErrorMessage());
        rsp.setSuccess(false);
        return rsp;
    }
}
