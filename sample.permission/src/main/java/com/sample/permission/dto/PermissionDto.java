package com.sample.permission.dto;

/**
 * Created by andongxu on 16-11-21.
 */
public class PermissionDto {

    private Long id;

    private ResourceDto resource;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ResourceDto getResource() {
        return resource;
    }

    public void setResource(ResourceDto resource) {
        this.resource = resource;
    }
}
