package com.sample.permission.repository;

import com.sample.permission.BaseTest;
import com.sample.permission.model.Permission;
import com.sample.permission.model.Role;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by andongxu on 16-8-25.
 */
@Transactional
@Commit
public class RoleRepositoryTest extends BaseTest {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Test
    public void insertRole0() {
        Role role = new Role();
        role.setName("manager");
        roleRepository.save(role);
    }

    @Test
    public void insertRole1() {
        Role parentRole = roleRepository.getOne(Long.valueOf(10000000));
        Role role = new Role();
        role.setParent(parentRole);
        role.setName("manager-dept");
        roleRepository.save(role);
    }
    @Test
    public void insertRole2() {
        Role parentRole = roleRepository.getOne(Long.valueOf(10000000));
        Role role = new Role();
        role.setParent(parentRole);
        role.setName("manager-dev");
        roleRepository.save(role);
    }
    @Test
    public void insertRole3() {
        Role parentRole = roleRepository.getOne(Long.valueOf(10000000));
        Role role = new Role();
        role.setParent(parentRole);
        role.setName("manager-test");
        roleRepository.save(role);
    }

    @Test
    public void insertRole4() {
        Role parentRole = roleRepository.getOne(Long.valueOf(10000010));
        Role role = new Role();
        role.setParent(parentRole);
        role.setName("dev-leader");
        roleRepository.save(role);
    }

    @Test
    public void updateRole0() {
        Role role = roleRepository.getOne(Long.valueOf(10000012));
        role.setName("manager-dev-leader");
        roleRepository.save(role);
    }

    @Test
    public void errorInsertRole0() {

        Role parentRole = new Role();
        parentRole.setName("temp");
        Role role = new Role();
        role.setParent(parentRole);
        role.setName("manager-dept");
        roleRepository.save(role);
    }

    @Test
    public void insertRolePermission() {
        Role role = roleRepository.getOne(Long.valueOf(10000000));
        role.setName("manager");
        Assert.assertNotNull(role);
        Permission permission = permissionRepository.getOne(Long.valueOf(10000000));
        Assert.assertNotNull(permission);
        Set<Permission> permissions = new HashSet<Permission>();
        permissions.add(permission);
        role.setPermissions(permissions);
        roleRepository.save(role);
    }

    @Test
    public void findRole0() {
        Role role = roleRepository.findOne(Long.valueOf(10000000));
        Assert.assertNotNull(role);
        Set<Role> sons = role.getSons();
        Assert.assertNotNull(sons);
    }

    @Test
    public void insertRole_cn() {
        Role role = new Role();
        role.setName("超级管理员");
        roleRepository.save(role);
    }
}
