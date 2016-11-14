package com.sample.configcenter.web;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by andongxu on 16-11-1.
 */
public class QueryProjectHandlerTest extends WebTest {

    @Test
    public void findAll() {
        String res = restTemplate.postForObject("http://127.0.0.1:8080/service/1001", "{\"reqId\":\"123456\"}", String.class);
        Assert.assertNotNull(res);
        System.out.println(res);
    }
}
