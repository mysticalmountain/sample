package com.sample.web.fastjson.ext;

import com.sample.core.service.ISampleService;
import com.sample.core.utils.AopTargetUtils;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/**
 * Created by andongxu on 16-11-10.
 */
public class SampleTypeReference {

    private final Type type;

    public SampleTypeReference(ISampleService sampleService) {
        Type superClass = sampleService.getClass().getGenericSuperclass();
        this.type = ((ParameterizedType)superClass).getActualTypeArguments()[0];
    }

    public Type getType() {
        return this.type;
    }
}
