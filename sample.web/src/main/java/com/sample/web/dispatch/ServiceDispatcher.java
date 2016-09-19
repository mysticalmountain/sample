package com.sample.web.dispatch;

import com.alibaba.fastjson.JSON;
import com.sample.core.exception.ExceptionLevel;
import com.sample.core.exception.UnifiedException;
import com.sample.core.log.Log;
import com.sample.core.log.Log4jLog;
import com.sample.core.service.ISampleService;
import com.sample.core.service.Service;
import com.sample.core.service.handler.IServiceHandler;
import com.sample.core.validator.FormatException;
import org.springframework.aop.TargetSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by andongxu on 9/11/16.
 */
@Component
public class ServiceDispatcher {

    private Log log = Log4jLog.getLog(this.getClass());

    @Autowired
    private IServiceVisitor serviceVisitor;
    @Autowired
    private List<ISampleService> sampleServices;


    public String execute(String serviceCode, final String requestData) throws Exception {
        log.info("request data --->" + requestData);
        for (ISampleService sampleService : sampleServices) {
            Method getTargetSource = sampleService.getClass().getMethod("getTargetSource");
            TargetSource ts = (TargetSource) getTargetSource.invoke(sampleService);
            Class targetClass = ts.getTargetClass();
            Method targetMethod = getMethod(targetClass, "doService");
            final Class[] parameterTypes = targetMethod.getParameterTypes();
            Service service = (Service) targetClass.getAnnotation(Service.class);
            if (serviceCode.equals(service.code())) {
                Object result = serviceVisitor.visit(sampleService, new IServiceData() {
                    @Override
                    public Object get() throws UnifiedException {
                        try {
                            String sourceDate = URLDecoder.decode(requestData, "utf-8");
                            return JSON.parseObject(sourceDate, parameterTypes[0]);
                        } catch (Exception e) {
                            log.error(e.getMessage(), e);
                            throw new FormatException(ExceptionLevel.SLIGHT, "999999", "数据格式化错误", "web", null, e);
                        }
                    }
                });
                String responseData= JSON.toJSONString(result);
                log.info("response data --->" + responseData);
                return responseData;
            }
        }
        throw new FormatException(ExceptionLevel.SERIOUS, "999999", "unknown exception", "web", null, null);
    }

    private Method getMethod(Class c, String methodName) {
        Method[] methods = c.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                if (method.getModifiers() == 1) {
                    return method;
                }
            }
        }
        return null;
    }
}
