package com.sample.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.sample.configcenter.dto.item.ItemDto;
import com.sample.core.model.dto.GenericSetReq;
import com.sample.permission.dto.EditChannelReq;
import com.sample.permission.dto.KeyValue;
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

    @Test
    public void obj2Json3() {
        GenericSetReq<ItemDto> items = new GenericSetReq<ItemDto>();
        ItemDto itemDto1 = new ItemDto();
        itemDto1.setId(Long.valueOf(1001));
        itemDto1.setKei("k1");
        itemDto1.setVal("v1");
        Set<ItemDto> itemDtos = new HashSet<ItemDto>();
        itemDtos.add(itemDto1);
        ItemDto itemDto2 = new ItemDto();
        itemDto2.setId(Long.valueOf(1001));
        itemDto2.setKei("k2");
        itemDto2.setVal("v2");
        itemDtos.add(itemDto2);

        items.setData(itemDtos);

        String res = JSON.toJSONString(items);
        System.out.println(res);
    }

    @Test
    public void json2obj3() {
       String str = "{\"reqid\":\"123456\",\"data\":[{\"kei\":\"k1\",\"val\":\"v1\",\"content\":\"c1\"},{\"kei\":\"k2\",\"val\":\"v2\",\"content\":\"c2\"}]}";
//        GenericSetReq<ItemDto> items = new GenericSetReq<ItemDto>();
//        Type superClass = items.getClass().getGenericSuperclass();
//        Type[] type = ((ParameterizedType)items.getClass().getGenericSuperclass()).getActualTypeArguments();
//        Type type = ((ParameterizedType)superClass).getActualTypeArguments()[0];
//        items.getData();
//        SampleTypeReference tr = new SampleTypeReference(){};


        GenericSetReq<ItemDto> genericSetReq = JSON.parseObject(str, new TypeReference<GenericSetReq<ItemDto>>(){});
//        GenericSetReq<ItemDto> genericSetReq = JSON.parseObject(str, new SampleTypeReference<i>);
        System.out.println();
    }
}
