package com.sample.test;

import com.alibaba.fastjson.JSON;
import com.sample.permission.dto.EditChannelReq;
import com.sample.permission.dto.EditRoleReq;
import com.sample.permission.dto.KeyValue;
import org.junit.*;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by andongxu on 9/12/16.
 */
public class FastJsonTest {

    @Test
    public void obj2Json() {
        EditChannelReq<String, String> editChannelReq = new EditChannelReq<String, String>();
        editChannelReq.setOwner("1001");
        String json = JSON.toJSONString(editChannelReq);
        System.out.println(json);
    }

    @Test
    public void obj2Json1() {
        EditChannelReq editChannelReq = new EditChannelReq();
        editChannelReq.setOwner("1001");
        KeyValue<String, String> kv1 = new KeyValue<String, String>();
        kv1.setK("k1");
        kv1.setV("v1");
        KeyValue<String, String> kv2 = new KeyValue<String, String>();
        kv2.setK("k2");
        kv2.setV("v2");
        Set<KeyValue<String, String>> keyValueSet = new HashSet<KeyValue<String, String>>();
        keyValueSet.add(kv1);
        keyValueSet.add(kv2);
        editChannelReq.setKeyValues(keyValueSet);
        String json = JSON.toJSONString(editChannelReq);
        System.out.println(json);
    }

    @Test
    public void json2Obj() {
        String json = "{\"owner\":\"1001\"}";
        EditChannelReq editChannelReq = JSON.parseObject(json, EditChannelReq.class);
        System.out.println(editChannelReq.getOwner());
    }

    @Test
    public void json2Obj1() {
        String json = "{\"keyValues\":[{\"k\":\"k2\",\"v\":\"v2\"},{\"k\":\"k1\",\"v\":\"v1\"}],\"owner\":\"1001\"}";
        EditChannelReq editChannelReq = JSON.parseObject(json, EditChannelReq.class);
        System.out.println(editChannelReq);
    }
}
