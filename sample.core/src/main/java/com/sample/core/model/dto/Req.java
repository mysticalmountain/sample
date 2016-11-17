package com.sample.core.model.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by andongxu on 16-6-15.
 */
public class Req implements Serializable {

    @NotNull(message = "不允许为空")
    private String reqId;

    private String userId;

    private String channelId;

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Req{" +
                "reqId='" + reqId + '\'' +
                ", userId='" + userId + '\'' +
                ", channelId='" + channelId + '\'' +
                '}';
    }
}
