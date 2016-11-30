package com.sample.permission.service.handler;

import com.sample.core.Constant;
import com.sample.core.exception.UnifiedException;
import com.sample.core.model.dto.GenericReq;
import com.sample.core.model.dto.GenericSetRsp;
import com.sample.core.model.dto.Req;
import com.sample.core.service.Service;
import com.sample.core.service.handler.AbstractServiceHandler;
import com.sample.permission.dto.PermissionDto;
import com.sample.permission.dto.QueryRoleDto;
import com.sample.permission.dto.ResourceDto;
import com.sample.permission.dto.RoleDto;
import com.sample.permission.model.Permission;
import com.sample.permission.model.Resource;
import com.sample.permission.model.Role;
import com.sample.permission.repository.RoleRepository;
import com.sample.permission.service.QueryRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by andongxu on 16-11-21.
 */
@Component
public class QueryRoleHandler extends AbstractServiceHandler<GenericReq<QueryRoleDto>, GenericSetRsp<RoleDto>> {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public boolean support(Object... objs) {
        Service service = (Service) objs[1];
        if (service.code().equals(QueryRoleService.class.getAnnotation(Service.class).code())) {
            return true;
        }
        return false;
    }

    @Override
    public GenericSetRsp<RoleDto> doHandle(GenericReq<QueryRoleDto> req, Service service) throws UnifiedException {
        List<Role> roles = null;
        if (req.getData() != null) {
            Role queryRole = new Role();
            BeanUtils.copyProperties(req.getData(), queryRole);
            Example<Role> example = Example.of(queryRole);
            roles = roleRepository.findAll(example);
        } else {
            roles = roleRepository.findAll();
        }
        Set<RoleDto> roleDtos = new HashSet<RoleDto>();
        for (Role role : roles) {
            RoleDto roleDto = new RoleDto();
            BeanUtils.copyProperties(role, roleDto);
            Set<Permission> permissions = role.getPermissions();
            Iterator<Permission> permissionIterator = permissions.iterator();
            Set<PermissionDto> permissionDtos = new HashSet<PermissionDto>();
            while (permissionIterator.hasNext()) {
                Permission permission = permissionIterator.next();
                PermissionDto permissionDto = new PermissionDto();
                BeanUtils.copyProperties(permission, permissionDto);
                Resource resource = permission.getResource();
                ResourceDto resourceDto = new ResourceDto();
                BeanUtils.copyProperties(resource, resourceDto);
                permissionDto.setResource(resourceDto);
                permissionDtos.add(permissionDto);
            }
            roleDto.setPermissions(permissionDtos);
            roleDtos.add(roleDto);
        }
        GenericSetRsp<RoleDto> genericSetRsp = new GenericSetRsp<RoleDto>();
        genericSetRsp.setData(roleDtos);
        genericSetRsp.setSuccess(true);
        genericSetRsp.setErrorCode(Constant.SUCCESS[0]);
        genericSetRsp.setErrorMsg(Constant.SUCCESS[1]);
        return genericSetRsp;
    }
}
