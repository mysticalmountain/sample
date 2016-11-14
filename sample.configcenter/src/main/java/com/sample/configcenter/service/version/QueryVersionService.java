package com.sample.configcenter.service.version;

import com.sample.configcenter.dto.version.VersionDto;
import com.sample.core.exception.UnifiedException;
import com.sample.core.model.dto.GenericReq;
import com.sample.core.model.dto.GenericSetRsp;
import com.sample.core.model.dto.GenericSetRsp;
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
@Service(code = "1022")
public class QueryVersionService extends AbstractSampleService<GenericReq<VersionDto>, GenericSetRsp<VersionDto>> {

    @Autowired
    private ServiceHandlerChain<GenericReq<VersionDto>, GenericSetRsp<VersionDto>> chain;

    @Override
    public GenericSetRsp<VersionDto> doService(GenericReq<VersionDto> versionDtoGenericReq) throws UnifiedException {
        return chain.handle(versionDtoGenericReq, this.getClass().getAnnotation(Service.class));
    }

    @Override
    public GenericSetRsp<VersionDto> captureException(GenericReq<VersionDto> versionDtoGenericReq, UnifiedException ue) throws UnifiedException {
        GenericSetRsp genericRsp = new GenericSetRsp();
        genericRsp.setErrorCode(ue.getErrorCode());
        genericRsp.setErrorMsg(ue.getErrorMessage());
        genericRsp.setSuccess(false);
        return genericRsp;
    }
}
