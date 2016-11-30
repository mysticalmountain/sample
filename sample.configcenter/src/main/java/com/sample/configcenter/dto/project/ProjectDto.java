package com.sample.configcenter.dto.project;

import com.sample.configcenter.dto.env.EnvDto;
import com.sample.configcenter.dto.version.VersionDto;

import java.io.Serializable;

/**
 * Created by andongxu on 16-11-1.
 */
public class ProjectDto implements Serializable{

    private Long id;

    private String name;

    private String content;

    private VersionDto versionDto;

    private EnvDto envDto;

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

    public VersionDto getVersionDto() {
        return versionDto;
    }

    public void setVersionDto(VersionDto versionDto) {
        this.versionDto = versionDto;
    }

    public EnvDto getEnvDto() {
        return envDto;
    }

    public void setEnvDto(EnvDto envDto) {
        this.envDto = envDto;
    }
}
