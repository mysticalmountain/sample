package com.sample.user.model.dto;

import com.sample.core.model.dto.Req;

/**
 * Created by andongxu on 16-6-15.
 */
public class UsernameReq extends Req {

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
