package com.sample.permission.dto;

import com.sample.core.model.dto.QueryReqDto;

/**
 * Created by andongxu on 9/10/16.
 */
public class QueryServiceReqDto extends QueryReqDto {

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
