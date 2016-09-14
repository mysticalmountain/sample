package com.sample.test;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by andongxu on 16-8-8.
 */
public class Test {

    private ThreadLocal<Integer> index = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return Integer.valueOf(1);
        }
    };

    public static void main(String [] args) throws Exception {

//        Math.
        System.out.println(Integer.toHexString(64));

    }
}
