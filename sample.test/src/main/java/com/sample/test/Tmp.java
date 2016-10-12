package com.sample.test;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andongxu on 16-8-17.
 */
public class Tmp {

    public static void main(String [] args) throws IOException {
        Process process = null;
        List<String> processList = new ArrayList<String>();
        try { ///home/andongxu/hdbank
            process = Runtime.getRuntime().exec("uncompress ckcpdhb.20160926.unl.00.Z", null, new File("/home/andongxu/hdbank"));
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";
            while ((line = input.readLine()) != null) {
                processList.add(line);
            }
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String line : processList) {
            System.out.println(line);
        }
    }
}
