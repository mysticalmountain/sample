package com.sample.configcenter.service.evn.handler;

import com.sample.configcenter.dto.env.EnvDto;
import com.sample.configcenter.model.Env;
import com.sample.configcenter.repository.EnvRepository;
import com.sample.configcenter.service.evn.QueryEnvService;
import com.sample.core.Constant;
import com.sample.core.exception.UnifiedException;
import com.sample.core.model.dto.GenericReq;
import com.sample.core.model.dto.GenericRsp;
import com.sample.core.service.Service;
import com.sample.core.service.handler.AbstractServiceHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by andongxu on 16-11-25.
 */
@Component
public class QueryEnvHandler extends AbstractServiceHandler<GenericReq<EnvDto>, GenericRsp<Set<EnvDto>>> {

    @Autowired
    private EnvRepository envRepository;

    @Override
    public boolean support(Object... objs) {
        Service service = (Service) objs[1];
        if (service.code().equals(QueryEnvService.class.getAnnotation(Service.class).code())) {
            GenericReq<EnvDto> genericReq = (GenericReq<EnvDto>) objs[0];
            EnvDto envDto = genericReq.getData();
            if (envDto == null || envDto.getId() == null) {
                return true;
            }
        }
        return false;
    }

    @Override
    public GenericRsp<Set<EnvDto>> doHandle(GenericReq<EnvDto> envDtoGenericReq, Service service) throws UnifiedException {
        List<Env> envs = envRepository.findAll();
        Set<EnvDto> envDtos = new HashSet<EnvDto>();
        for (Env env : envs) {
            EnvDto envDto = new EnvDto();
            BeanUtils.copyProperties(env, envDto);
            envDtos.add(envDto);
        }
        GenericRsp<Set<EnvDto>> genericRsp = new GenericRsp<Set<EnvDto>>();
        genericRsp.setData(envDtos);
        genericRsp.setErrorCode(Constant.SUCCESS[0]);
        genericRsp.setErrorMsg(Constant.SUCCESS[1]);
        genericRsp.setSuccess(true);
        return genericRsp;
    }
}
