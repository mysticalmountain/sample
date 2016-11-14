package com.sample.configcenter.dto.project;

import com.sample.core.model.dto.Req;

/**
 * Created by andongxu on 16-11-1.
 */
public class QueryProjectReq extends Req {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
