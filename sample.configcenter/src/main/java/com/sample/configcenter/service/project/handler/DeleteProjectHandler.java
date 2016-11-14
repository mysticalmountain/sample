package com.sample.configcenter.service.project.handler;

import com.sample.configcenter.repository.ProjectRepository;
import com.sample.core.Constant;
import com.sample.core.exception.UnifiedException;
import com.sample.core.model.dto.IdLongReq;
import com.sample.core.model.dto.Rsp;
import com.sample.core.service.Service;
import com.sample.core.service.handler.AbstractServiceHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 16-11-8.
 */
@Component
public class DeleteProjectHandler extends AbstractServiceHandler<IdLongReq, Rsp> {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public boolean support(Object ... objs) {
        if(objs[0] instanceof IdLongReq) {
            return true;
        }
        return false;
    }

    @Override
    public Rsp doHandle(IdLongReq idLongReq, Service service) throws UnifiedException {
        projectRepository.delete(idLongReq.getId());
        Rsp rsp = new Rsp();
        rsp.setErrorCode(Constant.SUCCESS[0]);
        rsp.setErrorMsg(Constant.SUCCESS[1]);
        rsp.setSuccess(true);
        return rsp;
    }
}
