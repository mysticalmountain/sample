package com.sample.permission.web;

import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

/**
 * Created by andongxu on 9/14/16.
 */
public class WebTest {

    protected RestTemplate restTemplate;

    public WebTest() {
        restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.ACCEPT, "application/json");
        httpHeaders.set(HttpHeaders.ACCEPT_ENCODING, "utf-8");
        httpHeaders.set(HttpHeaders.CONTENT_ENCODING, "utf-8");
        httpHeaders.add(HttpHeaders.CONNECTION, "Keep-Alive");
        httpHeaders.add(HttpHeaders.USER_AGENT, "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
    }

}
