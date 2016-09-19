package com.sample.test;

import com.sample.permission.dto.QueryServiceReq;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by andongxu on 16-9-2.
 */
public class BeanUtilTest {

    public static void main(String [] args) throws InvocationTargetException, IllegalAccessException {
//        ServiceDto s1 = new ServiceDto();
//        com.sample.permission.model.Service s2 = new com.sample.permission.model.Service();
//        s2.setCode("A");
//        s2.setId(Long.valueOf(1001));
//        BeanUtils.copyProperties(s1, s2);
//        System.out.println(s1);

        QueryServiceReq queryServiceReqDto = new QueryServiceReq();
        queryServiceReqDto.setId(Long.valueOf(2));

        Map<String, Object> ps = new HashMap<String, Object>();

        BeanUtils.populate(queryServiceReqDto, ps);

        System.out.println(ps);
    }
}
