package com.sample.user.model.dto;

import com.sample.core.model.dto.Req;
import com.sample.user.model.enums.UserType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by andongxu on 16-6-15.
 */
public class USC01001 extends Req {

    @Pattern(regexp = "\\w{6,32}", message = "格式:\\w{6,32}")
    @NotNull(message = "not null")
    private String username;

    private UserType userType;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return super.toString() + "USC01001{" +
                "username='" + username + '\'' +
                ", userType=" + userType +
                '}';
    }
}
