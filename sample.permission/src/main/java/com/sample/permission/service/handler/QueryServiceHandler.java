package com.sample.permission.service.handler;

import com.sample.core.exception.UnifiedException;
import com.sample.core.exception.UnifiedExceptionUtil;
import com.sample.core.model.dto.QueryRspDto;
import com.sample.core.service.handler.AbstractServiceHandler;
import com.sample.permission.dto.QueryReq;
import com.sample.permission.dto.QueryRsp;
import com.sample.permission.dto.QueryServiceReqDto;
import com.sample.permission.dto.ServiceDto;
import com.sample.permission.model.Service;
import com.sample.permission.repository.ServiceRepository;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andongxu on 16-9-1.
 */
@Component
public class QueryServiceHandler extends AbstractServiceHandler<QueryServiceReqDto, QueryRspDto<List<ServiceDto>>> {

    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public QueryRspDto<List<ServiceDto>> doHandle(final QueryServiceReqDto reqDto) throws UnifiedException {
        try {
            Service s = new Service();
            BeanUtils.copyProperties(s, reqDto);
            Example<Service> example = Example.of(s);
            QueryRspDto<List<ServiceDto>> queryRsp = new QueryRspDto<List<ServiceDto>>(true);
            List<ServiceDto> servicesDto = new ArrayList<ServiceDto>();
            if (reqDto.getPageNumber() > 0) {                   //分页
                Page<Service> services = serviceRepository.findAll(example, new Pageable() {
                    @Override
                    public int getPageNumber() {
                        return reqDto.getPageNumber() - 1;
                    }

                    @Override
                    public int getPageSize() {
                        return reqDto.getPageSize();
                    }

                    @Override
                    public int getOffset() {
                        return (reqDto.getPageNumber() -1)  * reqDto.getPageSize();
                    }

                    @Override
                    public Sort getSort() {
                        return null;
                    }

                    @Override
                    public Pageable next() {
                        return null;
                    }

                    @Override
                    public Pageable previousOrFirst() {
                        return null;
                    }

                    @Override
                    public Pageable first() {
                        return null;
                    }

                    @Override
                    public boolean hasPrevious() {
                        return false;
                    }
                });
                for (Service service : services) {
                    ServiceDto serviceDto = new ServiceDto();
                    BeanUtils.copyProperties(serviceDto, service);
                    servicesDto.add(serviceDto);
                }
                queryRsp.setTotalElements(services.getTotalElements());
                queryRsp.setTotalPages(services.getTotalPages());
            } else {                                            //不分页
                List<Service> services = serviceRepository.findAll(example);
                for (Service service : services) {
                    ServiceDto serviceDto = new ServiceDto();
                    BeanUtils.copyProperties(serviceDto, service);
                    servicesDto.add(serviceDto);
                }
            }
            BeanUtils.copyProperties(queryRsp, reqDto);
            queryRsp.setDate(servicesDto);
            return queryRsp;
        } catch (Exception e) {
            e.printStackTrace();
            UnifiedExceptionUtil.throwSeriousException(null, null, null, null, e);
            return null;
        }
    }

    @Override
    public boolean support(Object o) {
        if (o instanceof QueryServiceReqDto) {
            return true;
        }
        return false;
    }
}
