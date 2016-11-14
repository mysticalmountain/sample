package com.sample.configcenter.service.project.handler;

import com.sample.configcenter.dto.project.SaveProjectReq;
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
public class SaveProjectHandler extends AbstractServiceHandler<SaveProjectReq, Rsp> {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public boolean support(Object ... objs) {
        if (objs[0] instanceof SaveProjectReq) {
            return true;
        }
        return false;
    }

    @Override
    public Rsp doHandle(SaveProjectReq saveProjectReq, Service service) throws UnifiedException {
        Project project = new Project();
        BeanUtils.copyProperties(saveProjectReq, project);
        projectRepository.save(project);
        Rsp rsp = new Rsp();
        rsp.setErrorCode(Constant.SUCCESS[0]);
        rsp.setErrorMsg(Constant.SUCCESS[1]);
        rsp.setSuccess(true);
        return rsp;
    }
}
