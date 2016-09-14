package com.sample.permission.dto;

import com.sample.permission.model.PermissionType;

import java.io.Serializable;

/**
 * Created by andongxu on 16-8-29.
 */
public class ValidateReq implements Serializable{

    private String serviceCode;

    private String owner;

    private PermissionType permissionType;

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public PermissionType getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(PermissionType permissionType) {
        this.permissionType = permissionType;
    }
}
