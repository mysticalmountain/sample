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

/**
 * Created by andongxu on 16-11-25.
 */
@Component
public class QueryEnvByIdHandler extends AbstractServiceHandler<GenericReq<EnvDto>, GenericRsp<EnvDto>> {

    @Autowired
    private EnvRepository envRepository;

    @Override
    public boolean support(Object... objs) {
        Service service = (Service) objs[1];
        if (service.code().equals(QueryEnvService.class.getAnnotation(Service.class).code())) {
            GenericReq<EnvDto> genericReq = (GenericReq<EnvDto>) objs[0];
            EnvDto envDto = genericReq.getData();
            if (envDto != null && envDto.getId() != null) {
                return true;
            }
        }
        return false;
    }

    @Override
    public GenericRsp<EnvDto> doHandle(GenericReq<EnvDto> envDtoGenericReq, Service service) throws UnifiedException {
        Env env = envRepository.findOne(envDtoGenericReq.getData().getId());
        EnvDto envDto = new EnvDto();
        BeanUtils.copyProperties(env, envDto);
        GenericRsp<EnvDto> genericRsp = new GenericRsp<EnvDto>();
        genericRsp.setData(envDto);
        genericRsp.setSuccess(true);
        genericRsp.setErrorCode(Constant.SUCCESS[0]);
        genericRsp.setErrorMsg(Constant.SUCCESS[1]);
        return genericRsp;
    }
}
