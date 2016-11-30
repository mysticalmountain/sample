package com.sample.permission.service;

import com.sample.core.Constant;
import com.sample.core.exception.UnifiedException;
import com.sample.core.model.dto.GenericReq;
import com.sample.core.model.dto.GenericRsp;
import com.sample.core.model.dto.Rsp;
import com.sample.core.service.Service;
import com.sample.core.service.handler.AbstractServiceHandler;
import com.sample.permission.dto.EditRoleDto;
import com.sample.permission.dto.RoleDto;
import com.sample.permission.model.Operate;
import com.sample.permission.model.Permission;
import com.sample.permission.model.Resource;
import com.sample.permission.model.Role;
import com.sample.permission.repository.PermissionRepository;
import com.sample.permission.repository.ResourceRepository;
import com.sample.permission.repository.RoleRepository;
import com.sample.permission.repository.ServiceRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by andongxu on 16-11-22.
 */
@Component
public class EditRoleHandler extends AbstractServiceHandler<GenericReq<EditRoleDto>, Rsp> {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public boolean support(Object... objs) {
        Service service = (Service) objs[1];
        if (service.code().equals(EditRoleService.class.getAnnotation(Service.class).code())) {
            return true;
        }
        return false;
    }

    @Override
    public Rsp doHandle(GenericReq<EditRoleDto> genericReq, Service service) throws UnifiedException {
        EditRoleDto editRoleDto = genericReq.getData();
        Role role = null;
        if (editRoleDto.getId() == null) {
            role = new Role();
            BeanUtils.copyProperties(editRoleDto, role);
            role = roleRepository.save(role);
        } else {
            role = roleRepository.findOne(editRoleDto.getId());
            BeanUtils.copyProperties(editRoleDto, role);
        }
        Set<Permission> permissions = new HashSet<Permission>();
        for (String code : genericReq.getData().getServices()) {
            com.sample.permission.model.Service service1 = serviceRepository.findByCode(code);
            Resource resource = service1.getResource();
            Set<Permission> permissionSet = resource.getPermissions();
            Iterator<Permission> permissionIterator = permissionSet.iterator();
            Permission permission = null;
            if (permissionIterator.hasNext()) {
                permission = permissionIterator.next();
            } else {
                permission = new Permission();
                permission.setOperate(Operate.READ);
                permission.setResource(resource);
                permission = permissionRepository.save(permission);
            }
            permissions.add(permission);
        }
        role.setPermissions(permissions);
        roleRepository.save(role);
        Rsp rsp = new Rsp();
        rsp.setErrorCode(Constant.SUCCESS[0]);
        rsp.setErrorMsg(Constant.SUCCESS[1]);
        rsp.setSuccess(true);
        return rsp;
    }
}
