package com.sample.permission.service.handler;

import com.sample.core.exception.UnifiedException;
import com.sample.core.model.dto.GenericSetRsp;
import com.sample.core.model.dto.Req;
import com.sample.core.service.Service;
import com.sample.core.service.handler.AbstractServiceHandler;
import com.sample.permission.dto.ResourceDto;
import com.sample.permission.dto.ServiceDto;
import com.sample.permission.model.Resource;
import com.sample.permission.repository.ServiceRepository;
import com.sample.permission.service.QueryServiceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by andongxu on 16-11-21.
 */
@Component
public class QueryServiceHandler extends AbstractServiceHandler<Req, GenericSetRsp<ServiceDto>> {

    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public boolean support(Object... objs) {
        Service service = (Service)objs[1];
        if (service.code().equals(QueryServiceService.class.getAnnotation(Service.class).code())) {
            return  true;
        }
        return false;
    }

    @Override
    public GenericSetRsp<ServiceDto> doHandle(Req req, Service service) throws UnifiedException {
        List<com.sample.permission.model.Service> serviceList = serviceRepository.findAll();
        Set<ServiceDto> serviceDtoSet = new HashSet<ServiceDto>();
        for (com.sample.permission.model.Service service1 : serviceList) {
            ServiceDto serviceDto = new ServiceDto();
            BeanUtils.copyProperties(service1, serviceDto);
            Resource resource = service1.getResource();
            ResourceDto resourceDto = new ResourceDto();
            BeanUtils.copyProperties(resource, resourceDto);
            serviceDto.setResourceDto(resourceDto);
            serviceDtoSet.add(serviceDto);
        }
        GenericSetRsp<ServiceDto> serviceDtoGenericSetRsp = new GenericSetRsp<ServiceDto>();
        serviceDtoGenericSetRsp.setData(serviceDtoSet);
        serviceDtoGenericSetRsp.setSuccess(true);
        return serviceDtoGenericSetRsp;
    }
}
