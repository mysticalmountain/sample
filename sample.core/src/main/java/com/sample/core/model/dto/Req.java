package com.sample.core.model.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by andongxu on 16-6-15.
 */
public class Req implements Serializable {

    @NotNull(message = "不允许为空")
    private String reqId;

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    @Override
    public String toString() {
        return "Req{" +
                "reqId='" + reqId + '\'' +
                '}';
    }
}
