package com.sample.user.service.handler;

import com.sample.core.Constant;
import com.sample.core.exception.UnifiedException;
import com.sample.core.model.dto.GenericSetRsp;
import com.sample.core.model.dto.IdLongReq;
import com.sample.core.service.Service;
import com.sample.core.service.handler.AbstractServiceHandler;
import com.sample.permission.dto.RoleDto;
import com.sample.permission.model.Role;
import com.sample.user.model.User;
import com.sample.user.repository.UserRepository;
import com.sample.user.service.QueryUserRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by andongxu on 16-11-23.
 */
@Component
public class QueryUserRoleHandler extends AbstractServiceHandler<IdLongReq, GenericSetRsp<RoleDto>> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean support(Object... objs) {
        Service service = (Service)objs[1];
        if (service.code().equals(QueryUserRoleService.class.getAnnotation(Service.class).code())) {
            return true;
        }
        return false;
    }

    @Override
    public GenericSetRsp<RoleDto> doHandle(IdLongReq idLongReq, Service service) throws UnifiedException {
        User user = userRepository.findOne(idLongReq.getId());
        Set<Role> roleSet = user.getRoles();
        Iterator<Role> roleIterator = roleSet.iterator();
        Set<RoleDto> roleDtoSet = new HashSet<RoleDto>();
        while (roleIterator.hasNext()) {
            Role role = roleIterator.next();
            RoleDto roleDto = new RoleDto();
            BeanUtils.copyProperties(role, roleDto);
            roleDtoSet.add(roleDto);
        }
        GenericSetRsp<RoleDto> genericSetRsp = new GenericSetRsp<RoleDto>();
        genericSetRsp.setData(roleDtoSet);
        genericSetRsp.setErrorCode(Constant.SUCCESS[0]);
        genericSetRsp.setErrorMsg(Constant.SUCCESS[1]);
        genericSetRsp.setSuccess(false);
        return genericSetRsp;
    }
}
