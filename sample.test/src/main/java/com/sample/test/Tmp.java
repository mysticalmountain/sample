package com.sample.test;


import java.io.File;
import java.io.IOException;

/**
 * Created by andongxu on 16-8-17.
 */
public class Tmp {

    public static void main(String [] args) throws IOException {
        File file = new File("/home/andongxu/workspace/tmp-file");
        if (!file.exists()) {
            file.createNewFile();
        }
    }
}
