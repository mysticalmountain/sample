package com.sample.configcenter.dto.version;

import com.sample.configcenter.dto.project.ProjectDto;
import com.sample.configcenter.model.Project;
import com.sample.core.model.dto.Req;

/**
 * Created by andongxu on 16-11-10.
 */
public class VersionDto extends Req {

    private Long id;

    private String name;

    private String content;

    private Long projectId;

    private ProjectDto project;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public ProjectDto getProject() {
        return project;
    }

    public void setProject(ProjectDto project) {
        this.project = project;
    }
}
