package com.sample.user.service.handler;

import com.sample.core.exception.UnifiedException;
import com.sample.core.model.dto.GenericReq;
import com.sample.core.model.dto.GenericSetRsp;
import com.sample.core.service.Service;
import com.sample.core.service.handler.AbstractServiceHandler;
import com.sample.permission.dto.PermissionDto;
import com.sample.permission.dto.ResourceDto;
import com.sample.permission.model.Permission;
import com.sample.permission.model.Resource;
import com.sample.permission.model.Role;
import com.sample.user.dto.QueryPermissionDto;
import com.sample.user.model.User;
import com.sample.user.repository.UserRepository;
import com.sample.user.service.QueryPermissionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by andongxu on 16-11-18.
 */
@Component
public class QueryPermissionHandler extends AbstractServiceHandler<GenericReq<QueryPermissionDto>, GenericSetRsp<PermissionDto>> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean support(Object... objs) {
        Service service = (Service)objs[1];
        if (service.code().equals(QueryPermissionService.class.getAnnotation(Service.class).code())) {
            GenericReq genericReq = (GenericReq)objs[0];
            if (genericReq.getData() instanceof QueryPermissionDto) {
                return true;
            }
        }
        return false;
    }

    @Override
    public GenericSetRsp<PermissionDto> doHandle(GenericReq<QueryPermissionDto> queryPermissionDtoGenericReq, Service service) throws UnifiedException {
        User user = userRepository.findOne(queryPermissionDtoGenericReq.getData().getUserId());
        Set<Role> roleSet = user.getRoles();
        Iterator<Role> iterator = roleSet.iterator();
        Set<PermissionDto> permissionDtos = new HashSet<PermissionDto>();
        while (iterator.hasNext()) {
            Role role = iterator.next();
            Set<Permission> permissions = role.getPermissions();
            Iterator<Permission> iterator1 = permissions.iterator();
            while (iterator1.hasNext()) {
                Permission permission = iterator1.next();
                PermissionDto permissionDto = new PermissionDto();
                BeanUtils.copyProperties(permission, permissionDto);
                Resource resource = permission.getResource();
                ResourceDto resourceDto = new ResourceDto();
                BeanUtils.copyProperties(resource, resourceDto);
                permissionDto.setResource(resourceDto);
                permissionDtos.add(permissionDto);
            }
        }
        GenericSetRsp<PermissionDto> genericSetRsp = new GenericSetRsp<PermissionDto>();
        genericSetRsp.setData(permissionDtos);
        return genericSetRsp;
    }
}
