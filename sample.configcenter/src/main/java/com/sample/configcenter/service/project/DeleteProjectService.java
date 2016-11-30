package com.sample.configcenter.service.project;

import com.sample.core.exception.UnifiedException;
import com.sample.core.model.dto.IdLongReq;
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
@Service(code = "deleteProject", name = "删除项目")
public class DeleteProjectService extends AbstractSampleService<IdLongReq, Rsp> {

    @Autowired
    private ServiceHandlerChain<IdLongReq, Rsp> chain;

    @Override
    public Rsp doService(IdLongReq idLongReq) throws UnifiedException {
        return chain.handle(idLongReq, this.getClass().getAnnotation(Service.class));
    }

    @Override
    public Rsp captureException(IdLongReq idLongReq, UnifiedException ue) throws UnifiedException {
        Rsp rsp = new Rsp();
        rsp.setErrorCode(ue.getErrorCode());
        rsp.setErrorMsg(ue.getErrorMessage());
        rsp.setSuccess(false);
        return rsp;
    }
}
