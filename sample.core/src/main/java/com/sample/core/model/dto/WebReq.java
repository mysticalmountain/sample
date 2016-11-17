package com.sample.core.model.dto;

/**
 * Created by andongxu on 16-11-14.
 */
public class WebReq extends Req {

    private String sessionId;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
