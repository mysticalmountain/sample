package com.sample.permission.dto;

import java.io.Serializable;

/**
 * Created by andongxu on 16-9-2.
 */
public class QueryReq implements Serializable {

    private Long id;

    private String code;

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
}
