package com.sample.test.io;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by andongxu on 9/9/16.
 */
public class ReadVideo {

    public static void main(String [] args) throws Exception {
        URL url = new URL("http://prtas.videocc.net/v1/view?pid=1473399077057X1052185&uid=a0dedeea8b&vid=a0dedeea8b1aca214e1525dcaea2684b_a&flow=10409999&pd=25&sd=29&ts=1473399106899&sign=2d67ecb55a1fcc8aadc366778039edf8&session_id=&param1=&param2=&param3=&param4=&param5=160830&cts=6708&duration=7153&href=http%3A%2F%2Fzihua.com.cn%2Fcareercourse%2FGD616%2F16%2F71%2F2075");
        InputStream is = url.openStream();
        System.out.println(is.available());
//        Thread.currentThread().sleep(10000);
//        System.out.println(is.available());
        for (int i = 0; i != -1; i = is.read()) {
            System.out.println(i);
        }
        is.close();
    }
}
