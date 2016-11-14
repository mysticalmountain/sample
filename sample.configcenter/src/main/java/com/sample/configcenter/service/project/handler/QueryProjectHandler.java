package com.sample.configcenter.service.project.handler;

import com.sample.configcenter.dto.project.ProjectDto;
import com.sample.configcenter.dto.project.QueryProjectReq;
import com.sample.configcenter.model.Project;
import com.sample.configcenter.repository.ProjectRepository;
import com.sample.core.Constant;
import com.sample.core.exception.UnifiedException;
import com.sample.core.model.dto.QueryRsp;
import com.sample.core.service.Service;
import com.sample.core.service.handler.AbstractServiceHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andongxu on 16-11-1.
 */
@Component
public class QueryProjectHandler extends AbstractServiceHandler<QueryProjectReq, QueryRsp<List<ProjectDto>>> {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public boolean support(Object ... objs) {
        if (objs[0] instanceof QueryProjectReq) {
            return true;
        }
        return false;
    }

    @Override
    public QueryRsp<List<ProjectDto>> doHandle(QueryProjectReq queryProjectReq, Service service) throws UnifiedException {
        Project p = new Project();
        BeanUtils.copyProperties(queryProjectReq, p);
        Example<Project> example = Example.of(p);
        List<Project> projects = projectRepository.findAll(example);

        QueryRsp<List<ProjectDto>> queryRsp = new QueryRsp<List<ProjectDto>>();
        List<ProjectDto> projectDtos = new ArrayList<ProjectDto>();
        for (Project project : projects) {
            ProjectDto projectDto = new ProjectDto();
            BeanUtils.copyProperties(project, projectDto);
            projectDtos.add(projectDto);
        }
        BeanUtils.copyProperties(queryProjectReq, queryRsp);
        queryRsp.setData(projectDtos);
        queryRsp.setErrorCode(Constant.SUCCESS[0]);
        queryRsp.setErrorMsg(Constant.SUCCESS[1]);
        queryRsp.setSuccess(true);
        return queryRsp;
    }
}
