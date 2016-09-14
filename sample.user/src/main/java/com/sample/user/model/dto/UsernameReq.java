package com.sample.user.model.dto;

import com.sample.core.model.dto.ReqDto;

/**
 * Created by andongxu on 16-6-15.
 */
public class UsernameReq extends ReqDto {

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
