package com.sample.configcenter.service.item.handler;

import com.sample.configcenter.dto.item.ItemDto;
import com.sample.configcenter.model.Item;
import com.sample.configcenter.model.Project;
import com.sample.configcenter.model.Version;
import com.sample.configcenter.repository.ItemRepository;
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
import java.util.List;
import java.util.Set;

/**
 * Created by andongxu on 16-11-11.
 */
@Component
public class QueryItemHandler extends AbstractServiceHandler<GenericReq<ItemDto>, GenericSetRsp<ItemDto>> {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public boolean support(Object... objs) {
        Service service = (Service) objs[1];
        if (service.code().equals("1031")) {
            return true;
        }
        return false;
    }

    @Override
    public GenericSetRsp<ItemDto> doHandle(GenericReq<ItemDto> itemDtoGenericReq, Service service) throws UnifiedException {
        Item item = new Item();
        if (itemDtoGenericReq.getData() != null) {
            ItemDto itemDto = itemDtoGenericReq.getData();
            Project project = new Project();
            project.setId(itemDto.getProjectId());
            Version version = new Version();
            version.setId(itemDto.getVersionId());
            item.setProject(project);
            item.setVersion(version);
        }
        Example<Item> example = Example.of(item);
        List<Item> items = itemRepository.findAll(example);
        Set<ItemDto> itemDtoSet = new HashSet<ItemDto>();
        for (Item item1 : items) {
            ItemDto itemDto = new ItemDto();
            BeanUtils.copyProperties(item1, itemDto);
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
