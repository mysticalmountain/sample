package com.sample.permission.web.service;

import com.sample.core.exception.UnifiedException;
import com.sample.core.model.dto.QueryRsp;
import com.sample.permission.dto.QueryServiceReq;
import com.sample.permission.web.WebTest;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by andongxu on 9/18/16.
 */
public class QueryServiceServiceTest extends WebTest {

    @Test
    public void findAll() {
        String res = restTemplate.postForObject("http://127.0.0.1:8080/service/1003", "{\"reqId\":\"123456\"}", String.class);
        Assert.assertNotNull(res);
        System.out.println(res);
    }

    @Test
    public void findById() throws UnifiedException {
        String res = restTemplate.postForObject("http://127.0.0.1:8080/service/1003", "{\"reqId\":\"123456\", \"id\":10000007}", String.class);
        Assert.assertNotNull(res);
        System.out.println(res);
    }
}
