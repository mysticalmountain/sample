package com.sample.configcenter.service.version.handler;

import com.sample.configcenter.dto.version.VersionDto;
import com.sample.configcenter.model.Project;
import com.sample.configcenter.model.Version;
import com.sample.configcenter.repository.ProjectRepository;
import com.sample.configcenter.repository.VersionRepository;
import com.sample.configcenter.service.version.SaveVersionService;
import com.sample.core.Constant;
import com.sample.core.exception.UnifiedException;
import com.sample.core.model.dto.GenericReq;
import com.sample.core.model.dto.Rsp;
import com.sample.core.service.Service;
import com.sample.core.service.handler.AbstractServiceHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 16-11-10.
 */
@Component
public class SaveVersionHandler extends AbstractServiceHandler<GenericReq<VersionDto>, Rsp> {

    @Autowired
    private VersionRepository versionRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public boolean support(Object... objs) {
        Service service = (Service) objs[1];
        if (service.code().equals(SaveVersionService.class.getAnnotation(Service.class).code())) {
            return true;
        }
        return false;
    }

    @Override
    public Rsp doHandle(GenericReq<VersionDto> versionDtoGenericReq, Service service) throws UnifiedException {
        Version version = null;
        VersionDto versionDto = versionDtoGenericReq.getData();
        if (versionDtoGenericReq.getData().getId() != null) {
            version = versionRepository.findOne(versionDtoGenericReq.getData().getId());
        } else {
            version = new Version();
            Project project = projectRepository.findOne(versionDto.getProjectId());
            version.setProject(project);
        }
        BeanUtils.copyProperties(versionDto, version);
        versionRepository.save(version);
        Rsp rsp = new Rsp();
        rsp.setErrorCode(Constant.SUCCESS[0]);
        rsp.setErrorMsg(Constant.SUCCESS[1]);
        rsp.setSuccess(true);
        return rsp;
    }
}
