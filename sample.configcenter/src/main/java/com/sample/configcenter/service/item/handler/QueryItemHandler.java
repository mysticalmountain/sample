package com.sample.configcenter.service.item.handler;

import com.sample.configcenter.dto.item.ItemDto;
import com.sample.configcenter.model.*;
import com.sample.configcenter.repository.KeiRepository;
import com.sample.configcenter.service.item.QueryItemService;
import com.sample.core.Constant;
import com.sample.core.exception.UnifiedException;
import com.sample.core.model.dto.GenericReq;
import com.sample.core.model.dto.GenericSetRsp;
import com.sample.core.service.Service;
import com.sample.core.service.handler.AbstractServiceHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by andongxu on 16-11-11.
 */
@Component
public class QueryItemHandler extends AbstractServiceHandler<GenericReq<ItemDto>, GenericSetRsp<ItemDto>> {

    @Autowired
    private KeiRepository itemRepository;

    @Override
    public boolean support(Object... objs) {
        Service service = (Service) objs[1];
        if (service.code().equals(QueryItemService.class.getAnnotation(Service.class).code())) {
            return true;
        }
        return false;
    }

    @Override
    public GenericSetRsp<ItemDto> doHandle(GenericReq<ItemDto> itemDtoGenericReq, Service service) throws UnifiedException {
        Kei kei = new Kei();
        if (itemDtoGenericReq.getData() != null) {
            ItemDto itemDto = itemDtoGenericReq.getData();
            Project project = new Project();
            project.setId(itemDto.getProjectId());
            Version version = new Version();
            version.setId(itemDto.getVersionId());
            kei.setProject(project);
            kei.setVersion(version);
        }
        Example<Kei> example = Example.of(kei);
        List<Kei> items = itemRepository.findAll(example);
        Set<ItemDto> itemDtoSet = new HashSet<ItemDto>();
        for (Kei item1 : items) {
            ItemDto itemDto = new ItemDto();
            BeanUtils.copyProperties(item1, itemDto);
            Set<Val> vals = item1.getVals();
            if (vals != null) {
                Iterator<Val> valIterator = vals.iterator();
                while (valIterator.hasNext()) {
                    Val val = valIterator.next();
                    if (val.getEnv() != null && val.getEnv().getId().equals(itemDtoGenericReq.getData().getEnvId())) {
                        itemDto.setVal(val.getVal());
                        break;
                    }
                }
            }
            itemDtoSet.add(itemDto);
        }
        GenericSetRsp<ItemDto> genericSetRsp = new GenericSetRsp<ItemDto>();
        genericSetRsp.setData(itemDtoSet);
        genericSetRsp.setErrorCode(Constant.SUCCESS[0]);
        genericSetRsp.setErrorMsg(Constant.SUCCESS[1]);
        genericSetRsp.setSuccess(true);
        return genericSetRsp;
    }
}
