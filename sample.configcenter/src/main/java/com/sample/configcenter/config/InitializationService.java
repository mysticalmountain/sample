package com.sample.configcenter.config;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.sample.core.model.dto.QueryServiceRsp;
import com.sample.core.service.AbstractSampleService;
import com.sample.core.service.Service;
import com.sample.permission.model.Resource;
import com.sample.permission.model.ResourceType;
import com.sample.permission.repository.ResourceRepository;
import com.sample.permission.repository.ServiceRepository;
import org.springframework.aop.TargetSource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by andongxu on 16-11-21.
 */
@Component
public class InitializationService implements ApplicationListener {


    @Autowired(required = false)
    private List<AbstractSampleService> abstractSampleServiceList;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private ResourceRepository resourceRepository;

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        Set<QueryServiceRsp> service1s = new HashSet<QueryServiceRsp>();
        for (int i = 0; i < abstractSampleServiceList.size(); i++) {
            Object sampleService = abstractSampleServiceList.get(i);
            Service service = null;
            try {
                Method getTargetSource = sampleService.getClass().getMethod("getTargetSource");
                TargetSource ts = (TargetSource) getTargetSource.invoke(sampleService);
                Class targetClass = ts.getTargetClass();
                service = (Service) targetClass.getAnnotation(Service.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            com.sample.permission.model.Service service1 = new com.sample.permission.model.Service();
            service1.setCode(service.code());
            service1.setContent(service.name());
            service1.setSystem(service.system());
            service1.setModule(service.module());
            service1.setValidateReq(service.isValidateReq());
            service1.setWriteLog(service.isWriteLog());
            com.sample.permission.model.Service service2 = serviceRepository.findByCode(service.code());
            if (service2 != null) {
//                BeanUtils.copyProperties(service1, service2);
                service2.setContent(service1.getContent());
                service2.setWriteLog(service1.getWriteLog());
                service2.setValidateReq(service1.getValidateReq());
                service2.setIdempotent(service1.getIdempotent());
                serviceRepository.save(service2);
            } else {
                Resource resource = new Resource();
                resource.setResourceType(ResourceType.SERVICE);
                resource = resourceRepository.save(resource);
                service1.setResource(resource);
                serviceRepository.save(service1);
            }
        }
    }

}
