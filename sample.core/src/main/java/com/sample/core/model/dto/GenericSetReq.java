package com.sample.core.model.dto;

import java.util.Set;

/**
 * Created by andongxu on 16-11-9.
 */
public class GenericSetReq<T> extends Req {

    private Set<T> data;

    public Set<T> getData() {
        return data;
    }

    public void setData(Set<T> data) {
        this.data = data;
    }
}
