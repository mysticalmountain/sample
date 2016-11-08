package com.sample.core.model.dto;

import javax.validation.constraints.NotNull;

/**
 * Created by andongxu on 16-11-8.
 */
public class IdLongReq extends Req {

    @NotNull(message = "不允许为空")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
