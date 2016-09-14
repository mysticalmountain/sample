package com.sample.web.controller;

import com.sample.web.HttpRequest;

/**
 * Created by andongxu on 9/12/16.
 */
public class Test {

    public static void main(String [] args) {
        String url = "http://127.0.0.1:8080/service/1001";
        String data = "{\"owner\":\"WX\"}ff";
        HttpRequest httpRequest = new HttpRequest();
        String res = httpRequest.sendPost(url, data);
        System.out.println(res);
    }
}
