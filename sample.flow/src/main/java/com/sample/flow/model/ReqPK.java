package com.sample.flow.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by andongxu on 9/20/16.
 */
@Embeddable
public class ReqPK implements Serializable {

    @Column(length = 8, name = "service_code")
    private String serviceCode;

    @Column(length = 64, name = "request_id")
    private String requestId;

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
