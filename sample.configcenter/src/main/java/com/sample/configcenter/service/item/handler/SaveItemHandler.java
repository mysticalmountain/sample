package com.sample.configcenter.service.item.handler;

import com.sample.configcenter.dto.item.ItemDto;
import com.sample.configcenter.model.Item;
import com.sample.configcenter.model.Project;
import com.sample.configcenter.model.Version;
import com.sample.configcenter.repository.ItemRepository;
import com.sample.configcenter.repository.ProjectRepository;
import com.sample.configcenter.repository.VersionRepository;
import com.sample.core.Constant;
import com.sample.core.exception.UnifiedException;
import com.sample.core.model.dto.GenericSetReq;
import com.sample.core.model.dto.Rsp;
import com.sample.core.service.Service;
import com.sample.core.service.handler.AbstractServiceHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by andongxu on 16-11-9.
 */
@Component
public class SaveItemHandler extends AbstractServiceHandler<GenericSetReq<ItemDto>, Rsp> {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private VersionRepository versionRepository;

    @Override
    public boolean support(Object... objs) {
        Service service = (Service)objs[1];
        if (service.code().equals("1030")) {
            return true;
        }
        return false;
    }

    @Override
    public Rsp doHandle(GenericSetReq<ItemDto> itemDtoGenericSetReq, Service service) throws UnifiedException {
        Set<ItemDto> itemDtos = itemDtoGenericSetReq.getData();
        Iterator<ItemDto> itemDtoIterator = itemDtos.iterator();
        while (itemDtoIterator.hasNext()) {
            ItemDto itemDto = itemDtoIterator.next();
            Item item = null;
            if (itemDto.getId() != null) {
                item = itemRepository.findOne(itemDto.getId());
            }
            if (item == null) {
                item = new Item();
            }
            BeanUtils.copyProperties(itemDto, item);
            Project project = projectRepository.findOne(itemDto.getProjectId());
            item.setProject(project);
            Version version = versionRepository.findOne(itemDto.getVersionId());
            item.setVersion(version);
            itemRepository.save(item);
        }
        Rsp rsp = new Rsp();
        rsp.setErrorCode(Constant.SUCCESS[0]);
        rsp.setErrorMsg(Constant.SUCCESS[1]);
        rsp.setSuccess(true);
        return rsp;
    }
}
