package com.sample.test;

import org.junit.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by andongxu on 9/12/16.
 */
public class UrlEncode {

    @org.junit.Test
    public void decode() throws UnsupportedEncodingException {
        String s = "%7B%22owner%22%3A%221001%22%7D=";
        String r = URLDecoder.decode(s, "utf-8");
        System.out.println(r.substring(0, r.length() -1));
    }
}
