package com.sample.permission.repository;

import com.sample.permission.BaseTest;
import com.sample.permission.model.Menu;
import com.sample.permission.model.Permission;
import com.sample.permission.model.PermissionType;
import com.sample.permission.model.Role;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by andongxu on 16-8-26.
 */
@Transactional
@Commit
public class PermissionMenuTest extends BaseTest {

    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void addPermission0() {
        Role role = roleRepository.getOne(Long.valueOf(10000010));
        Permission permission = new Permission();
        permission.setPermissionType(PermissionType.MENU);
        Menu menu = menuRepository.getOne(Long.valueOf(10000018));
        Set<Menu> menus = new HashSet<Menu>();
        menus.add(menu);
        permission.setMenus(menus);
        Set<Permission> permissions = new HashSet<Permission>();
        permissions.add(permission);
        role.setPermissions(permissions);
        roleRepository.save(role);
    }

    @Test
    public void addPermission１() {
        Permission permission = new Permission();
        permission.setPermissionType(PermissionType.MENU);
        Menu menu = menuRepository.getOne(Long.valueOf(10000018));
        Set<Menu> menus = new HashSet<Menu>();
        menus.add(menu);
        menus.addAll(menu.getSons());
        permission.setMenus(menus);
        permissionRepository.save(permission);
    }

    @Test
    public void addPermission2() {
        Permission permission = new Permission();
        permission.setPermissionType(PermissionType.MENU);
//        Menu menu = menuRepository.getOne(Long.valueOf(10000018));
//        Set<Menu> menus = menu.getSons();
//        for (Menu m : menus) {
//
//        }
////        menus.add(menu);
        Menu m1 = new Menu();
        m1.setName("m1");
        Set<Menu> menus = new HashSet<Menu>();
        menus.add(m1);
        permission.setMenus(menus);

        permissionRepository.save(permission);
    }

}
