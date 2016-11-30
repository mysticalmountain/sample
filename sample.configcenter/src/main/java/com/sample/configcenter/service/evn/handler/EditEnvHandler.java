package com.sample.configcenter.service.evn.handler;

import com.sample.configcenter.dto.env.EnvDto;
import com.sample.configcenter.model.Env;
import com.sample.configcenter.repository.EnvRepository;
import com.sample.configcenter.service.evn.EditEnvService;
import com.sample.core.Constant;
import com.sample.core.exception.UnifiedException;
import com.sample.core.model.dto.GenericReq;
import com.sample.core.model.dto.Rsp;
import com.sample.core.service.Service;
import com.sample.core.service.handler.AbstractServiceHandler;
import com.sun.scenario.effect.impl.Renderer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 16-11-25.
 */
@Component
public class EditEnvHandler extends AbstractServiceHandler<GenericReq<EnvDto>, Rsp> {

    @Autowired
    private EnvRepository envRepository;

    @Override
    public boolean support(Object... objs) {
        Service service = (Service) objs[1];
        if (service.code().equals(EditEnvService.class.getAnnotation(Service.class).code())) {
            return true;
        }
        return false;
    }

    @Override
    public Rsp doHandle(GenericReq<EnvDto> envDtoGenericReq, Service service) throws UnifiedException {
        Env env = null;
        if (envDtoGenericReq.getData().getId() == null) {
            env = new Env();
        } else {
            env = envRepository.findOne(envDtoGenericReq.getData().getId());
        }
        BeanUtils.copyProperties(envDtoGenericReq.getData(), env);
        envRepository.save(env);
        Rsp rsp = new Rsp();
        rsp.setErrorCode(Constant.SUCCESS[0]);
        rsp.setErrorMsg(Constant.SUCCESS[1]);
        rsp.setSuccess(true);
        return rsp;
    }
}
