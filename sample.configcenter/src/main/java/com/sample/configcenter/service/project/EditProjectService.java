package com.sample.configcenter.service.project;

import com.sample.configcenter.dto.project.EditProjectReq;
import com.sample.core.exception.UnifiedException;
import com.sample.core.model.dto.Rsp;
import com.sample.core.service.AbstractSampleService;
import com.sample.core.service.Service;
import com.sample.core.service.handler.ServiceHandlerChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 16-11-8.
 */
@Component
@Service(code = "1005", isValidateReq = true)
public class EditProjectService extends AbstractSampleService<EditProjectReq, Rsp> {

    @Autowired
    private ServiceHandlerChain<EditProjectReq, Rsp> chain;

    @Override
    public Rsp doService(EditProjectReq editProjectReq) throws UnifiedException {
        return chain.handle(editProjectReq, this.getClass().getAnnotation(Service.class));
    }

    @Override
    public Rsp captureException(EditProjectReq editProjectReq, UnifiedException ue) throws UnifiedException {
        Rsp rsp = new Rsp();
        rsp.setErrorCode(ue.getErrorCode());
        rsp.setErrorMsg(ue.getErrorMessage());
        rsp.setSuccess(false);
        return rsp;
    }
}
