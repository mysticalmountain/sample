package com.sample.permission.service.handler;

import com.sample.core.Constant;
import com.sample.core.exception.UnifiedException;
import com.sample.core.model.dto.GenericRsp;
import com.sample.core.model.dto.IdLongReq;
import com.sample.core.service.Service;
import com.sample.core.service.handler.AbstractServiceHandler;
import com.sample.permission.dto.MenuDto;
import com.sample.permission.model.Menu;
import com.sample.permission.repository.MenuRepository;
import com.sample.permission.service.QueryMenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by andongxu on 16-11-24.
 */
@Component
public class QueryMenuHandler extends AbstractServiceHandler<IdLongReq, GenericRsp<MenuDto>> {

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public boolean support(Object... objs) {
        Service service = (Service)objs[1];
        if (service.code().equals(QueryMenuService.class.getAnnotation(Service.class).code())) {
            return true;
        }
        return false;
    }

    @Override
    public GenericRsp<MenuDto> doHandle(IdLongReq req, Service service) throws UnifiedException {
        Menu menu = menuRepository.findOne(req.getId());
        MenuDto menuDto = new MenuDto();
        BeanUtils.copyProperties(menu, menuDto);
        assembleTree(menu, menuDto);
        GenericRsp<MenuDto> genericRsp = new GenericRsp<MenuDto>();
        genericRsp.setData(menuDto);
        genericRsp.setSuccess(true);
        return genericRsp;
    }

    public void assembleTree(Menu menu, MenuDto menuDto) {
        Set<Menu> menuSet = menu.getSons();
        Iterator<Menu> menuIterator = menuSet.iterator();
        while ((menuIterator.hasNext())) {
            Menu sonMenu = menuIterator.next();
            MenuDto sonMenuDto = new MenuDto();
            BeanUtils.copyProperties(sonMenu, sonMenuDto);
//            sonMenuDto.setName(sonMenu.getName());
            if (menuDto.getChildren() != null) {
                menuDto.getChildren().add(sonMenuDto);
//                sonMenuDto.setMenuParent(menuDto);
            } else {
                Set<MenuDto> sons = new HashSet<MenuDto>();
                sons.add(sonMenuDto);
                menuDto.setChildren(sons);
//                sonMenuDto.setMenuParent(menuDto);
            }
            assembleTree(sonMenu, sonMenuDto);
        }
    }
}
