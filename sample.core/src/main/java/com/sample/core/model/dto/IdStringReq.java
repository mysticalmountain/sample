package com.sample.core.model.dto;

import javax.validation.constraints.NotNull;

/**
 * Created by andongxu on 16-11-8.
 */
public class IdStringReq extends Req {

    @NotNull(message = "不允许为空")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
