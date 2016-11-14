package com.sample.configcenter.service.project.handler;

import com.sample.configcenter.dto.project.EditProjectReq;
import com.sample.configcenter.model.Project;
import com.sample.configcenter.repository.ProjectRepository;
import com.sample.core.Constant;
import com.sample.core.exception.UnifiedException;
import com.sample.core.model.dto.Rsp;
import com.sample.core.service.Service;
import com.sample.core.service.handler.AbstractServiceHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 16-11-8.
 */
@Component
public class EditProjectHandler extends AbstractServiceHandler<EditProjectReq, Rsp> {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public boolean support(Object ... objs) {
        Service service = (Service)objs[1];
        if (service.code().equals("1005")) {
            return true;
        }
        return false;
    }

    @Override
    public Rsp doHandle(EditProjectReq editProjectReq, Service service) throws UnifiedException {
        Project project = projectRepository.getOne(editProjectReq.getId());
        BeanUtils.copyProperties(editProjectReq, project);
        projectRepository.save(project);
        Rsp rsp = new Rsp();
        rsp.setErrorCode(Constant.SUCCESS[0]);
        rsp.setErrorMsg(Constant.SUCCESS[1]);
        rsp.setSuccess(true);
        return rsp;
    }
}
