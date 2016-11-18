package com.sample.permission.repository;

import com.sample.permission.BaseTest;
import com.sample.permission.model.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by andongxu on 16-11-15.
 */
public class ServiceRepositoryTest extends BaseTest {

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Test
    public void save1() {
        Resource r1 = new Resource();
        r1.setResourceType(ResourceType.SERVICE);
        r1 = resourceRepository.save(r1);
        Service service = new Service();
        service.setCode("1001");
        service.setContent("1001");
        service.setResource(r1);
        serviceRepository.save(service);
    }
    @Test
    public void save2() {
        Resource r1 = new Resource();
        r1.setResourceType(ResourceType.SERVICE);
        r1 = resourceRepository.save(r1);
        Service service = new Service();
        service.setCode("1022");
        service.setContent("1022");
        service.setResource(r1);
        serviceRepository.save(service);
    }

    @Test
    public void save3() {
        Resource resource = resourceRepository.getOne(Long.valueOf(10000060));
        Permission permission = new Permission();
        permission.setResource(resource);
        permission.setOperate(Operate.READ);
        permissionRepository.save(permission);
    }

    @Test
    public void save4() {
        Permission permission = permissionRepository.findOne(Long.valueOf(10000000));
        Role role = new Role();
        role.setName("admin");
        Set<Permission> permissions = new HashSet<Permission>();
        permissions.add(permission) ;
        role.setPermissions(permissions);
        roleRepository.save(role);
    }
}
