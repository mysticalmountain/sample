package com.sample.user.model.dto;

import com.sample.core.model.dto.Rsp;

/**
 * Created by andongxu on 16-6-15.
 */
public class UserIdRsp extends Rsp {

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
