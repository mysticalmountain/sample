package com.sample.configcenter.service.item.handler;

import com.sample.configcenter.dto.item.ItemDto;
import com.sample.configcenter.model.*;
import com.sample.configcenter.repository.*;
import com.sample.configcenter.service.item.EditItemService;
import com.sample.core.Constant;
import com.sample.core.exception.UnifiedException;
import com.sample.core.model.dto.GenericReq;
import com.sample.core.model.dto.Rsp;
import com.sample.core.service.Service;
import com.sample.core.service.handler.AbstractServiceHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by andongxu on 16-11-29.
 */
@Component
public class EditItemHandler extends AbstractServiceHandler<GenericReq<Set<ItemDto>>, Rsp> {

    @Autowired
    private KeiRepository keiRepository;
    @Autowired
    private ValRepository valRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private VersionRepository versionRepository;
    @Autowired
    private EnvRepository envRepository;

    @Override
    public boolean support(Object... objs) {
        Service service = (Service) objs[1];
        if (service.code().equals(EditItemService.class.getAnnotation(Service.class).code())) {
            return true;
        }
        return false;
    }

    @Override
    public Rsp doHandle(GenericReq<Set<ItemDto>> setGenericReq, Service service) throws UnifiedException {
        Set<ItemDto> itemDtos = setGenericReq.getData();
        Iterator<ItemDto> itemDtoIterator = itemDtos.iterator();
        while ((itemDtoIterator.hasNext())) {
            ItemDto itemDto = itemDtoIterator.next();
            if (itemDto.getId() != null) {
                Kei kei = keiRepository.findByKei(itemDto.getKei());
                if (kei != null) {
                    Set<Val> vals = kei.getVals();
                    Iterator<Val> valIterator = vals.iterator();
                    boolean isExist = false;
                    Val val = null;
                    while (valIterator.hasNext()) {
                        val = valIterator.next();
                        if (val == null) {
                            break;
                        }
                        if (val.getEnv() != null && val.getEnv().getId().equals(itemDto.getEnvId())) {
                            isExist = true;
                        }
                    }
                    if (isExist) {
                        val.setVal(itemDto.getVal());
                    } else {
                        val = new Val();
                        val.setKei(kei);
                        val.setVal(itemDto.getVal());
                    }
                    valRepository.save(val);
                } else {
                    kei = new Kei();
                    kei.setKei(itemDto.getKei());
                    kei.setContent(itemDto.getContent());
                    Project project = projectRepository.findOne(itemDto.getProjectId());
                    Version version = versionRepository.findOne(itemDto.getVersionId());
                    Env env = envRepository.findOne(itemDto.getEnvId());
                    kei.setProject(project);
                    kei.setVersion(version);
                    keiRepository.save(kei);
                    Val val = new Val();
                    val.setKei(kei);
                    val.setVal(itemDto.getVal());
                    val.setEnv(env);
                    valRepository.save(val);
                }
            }
        }
        Rsp rsp = new Rsp();
        rsp.setErrorCode(Constant.SUCCESS[0]);
        rsp.setErrorMsg(Constant.SUCCESS[1]);
        rsp.setSuccess(true);
        return rsp;
    }
}
