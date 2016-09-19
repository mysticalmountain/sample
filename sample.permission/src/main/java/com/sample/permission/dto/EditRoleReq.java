package com.sample.permission.dto;

import com.sample.core.model.dto.Req;

import java.util.Set;

/**
 * Created by andongxu on 16-8-31.
 */
public class EditRoleReq<K, V> extends Req {

    private String owner;

    private Set<KeyValue<K, V>> keyValues;


    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Set<KeyValue<K, V>> getKeyValues() {
        return keyValues;
    }

    public void setKeyValues(Set<KeyValue<K, V>> keyValues) {
        this.keyValues = keyValues;
    }

    @Override
    public String toString() {
        return "EditRoleReq{" +
                "owner='" + owner + '\'' +
                ", keyValues=" + keyValues +
                '}';
    }
}
