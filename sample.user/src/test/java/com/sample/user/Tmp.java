package com.sample.user;

import com.sample.core.log.TransLog;
import com.sample.core.model.dto.Rsp;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by andongxu on 16-6-22.
 */
public class Tmp {


    public static void main(String [] args) throws Exception {

        Tmp tmp = Tmp.class.newInstance();
        Method method = tmp.getClass().getMethod("setMe", String.class);
        Annotation [] ans = method.getAnnotations();
        System.out.println(ans.length + "\t" + ans);
        for (Annotation an : ans) {
            System.out.println(an);
        }

        TransLog transLog = method.getAnnotation(TransLog.class);
        System.out.println(transLog.system());

    }


    @TransLog(system = "AAAA")
    public Rsp setMe(String me) {
        System.out.println("-=======me");
        return null;
    }
}
