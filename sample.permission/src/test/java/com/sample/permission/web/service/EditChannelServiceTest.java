package com.sample.permission.web.service;

import com.sample.permission.web.WebTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpHeaders;

/**
 * Created by andongxu on 9/18/16.
 */
public class EditChannelServiceTest extends WebTest {


    @Test
    public void reqIdIsNull() {
        String res = restTemplate.postForObject("http://127.0.0.1:8080/service/1001", "{\"owner\":\"WX\", \"reqId\":\"123456\"}", String.class);
        Assert.assertNotNull(res);
        System.out.println(res);
    }

}
