package com.sample.configcenter.service.version.handler;

import com.sample.configcenter.dto.project.ProjectDto;
import com.sample.configcenter.dto.version.VersionDto;
import com.sample.configcenter.model.Project;
import com.sample.configcenter.model.Version;
import com.sample.configcenter.repository.VersionRepository;
import com.sample.configcenter.service.version.QueryVersionService;
import com.sample.core.Constant;
import com.sample.core.exception.UnifiedException;
import com.sample.core.model.dto.GenericReq;
import com.sample.core.model.dto.GenericSetRsp;
import com.sample.core.service.Service;
import com.sample.core.service.handler.AbstractServiceHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by andongxu on 16-11-10.
 */
@Component
public class QueryVersionHandler extends AbstractServiceHandler<GenericReq<VersionDto>, GenericSetRsp<VersionDto>> {

    @Autowired
    private VersionRepository versionRepository;

    @Override
    public boolean support(Object... objs) {
        Service service = (Service)objs[1];
        if (service.code().equals(QueryVersionService.class.getAnnotation(Service.class).code())) {
            return true;
        }
        return false;
    }

    @Override
    public GenericSetRsp<VersionDto> doHandle(GenericReq<VersionDto> versionDtoGenericReq, Service service) throws UnifiedException {
        Version v = new Version();
        if (versionDtoGenericReq.getData() != null && versionDtoGenericReq.getData().getProjectId() != null) {
            Project project = new Project();
            project.setId(versionDtoGenericReq.getData().getProjectId());
            v.setProject(project);
        }
        Example<Version> example = Example.of(v);
        List<Version> versions = versionRepository.findAll(example);
        Set<VersionDto> versionDtoSet = new HashSet<VersionDto>();


        for (Version version : versions) {
            VersionDto versionDto = new VersionDto();
            BeanUtils.copyProperties(version, versionDto);
            ProjectDto projectDto = new ProjectDto();
            Project project = version.getProject();
            BeanUtils.copyProperties(project, projectDto);
            versionDto.setProject(projectDto);
            versionDtoSet.add(versionDto);
        }
        GenericSetRsp<VersionDto> genericSetRsp = new GenericSetRsp<VersionDto>();
        genericSetRsp.setData(versionDtoSet);
        genericSetRsp.setErrorCode(Constant.SUCCESS[0]);
        genericSetRsp.setErrorCode(Constant.SUCCESS[1]);
        genericSetRsp.setSuccess(true);
        return genericSetRsp;
    }
}
