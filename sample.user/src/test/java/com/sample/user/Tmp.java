package com.sample.user;

import com.sample.core.exception.UnifiedException;
import com.sample.core.log.TransLog;
import com.sample.core.model.dto.ReqDto;
import com.sample.core.model.dto.RspDto;
import com.sample.core.service.IProcessor;
import com.sample.user.model.dto.USC01001;

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
    public RspDto setMe(String me) {
        System.out.println("-=======me");
        return null;
    }
}
