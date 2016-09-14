package com.sample.permission.dto;

import java.io.Serializable;

/**
 * Created by andongxu on 16-9-2.
 */
public class ServiceDto implements Serializable {

    private Long id;

    private String code;

    private String className;

    private String tmp;

    public String getTmp() {
        return tmp;
    }

    public void setTmp(String tmp) {
        this.tmp = tmp;
    }

    public Long getId() {
        return id;

    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
