package com.sample.permission.dto;

import java.util.List;

/**
 * Created by andongxu on 16-11-21.
 */
public class EditRoleDto {

    private Long id;

    private String name;

    private List<String> services;

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

    public List<String> getServices() {
        return services;
    }

    public void setServices(List<String> services) {
        this.services = services;
    }
}
