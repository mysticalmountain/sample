package com.sample.configcenter.service.project;

import com.sample.configcenter.dto.project.SaveProjectReq;
import com.sample.core.exception.UnifiedException;
import com.sample.core.model.dto.Rsp;
import com.sample.core.service.AbstractSampleService;
import com.sample.core.service.Service;
import com.sample.core.service.handler.ServiceHandlerChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 16-11-7.
 */
@Component
@Service(code = "1002", isValidateReq = true)
public class SaveProjectService extends AbstractSampleService<SaveProjectReq, Rsp> {

    @Autowired
    private ServiceHandlerChain<SaveProjectReq, Rsp> chain;

    @Override
    public Rsp doService(SaveProjectReq saveProjectReq) throws UnifiedException {
        return chain.handle(saveProjectReq, this.getClass().getAnnotation(Service.class));
    }

    @Override
    public Rsp captureException(SaveProjectReq saveProjectReq, UnifiedException ue) throws UnifiedException {
        Rsp rsp = new Rsp();
        rsp.setErrorCode(ue.getErrorCode());
        rsp.setErrorMsg(ue.getErrorMessage());
        rsp.setSuccess(false);
        return rsp;
    }
}
