package com.sample.permission.repository;

import com.sample.permission.BaseTest;
import com.sample.permission.model.Menu;
import com.sample.permission.model.Permission;
import com.sample.permission.model.Service;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by andongxu on 16-8-26.
 */
@Transactional
@Commit
public class MenuRepositoryTest extends BaseTest {

    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private PermissionRepository permissionRepository;

    @Test
    public void initMenu() {
        Menu menu = new Menu();
        menu.setName("fee center");
        menu = menuRepository.save(menu);
        menuRepository.flush();
        Menu menu1 = new Menu();
        menu1.setParent(menu);
        menu1.setName("account summary");
        Menu menu2 = new Menu();
        menu2.setParent(menu);
        menu2.setName("account flow");
//        Set<Menu> sons = new HashSet<Menu>();
//        sons.add(menu1);
//        sons.add(menu2);
//        menu.setSons(sons);
        menuRepository.save(menu1);
        menuRepository.save(menu2);

    }

    @Test
    public void insertMenu_cn() {
        Menu menu = new Menu();
        menu.setName("用户中心");
        menuRepository.save(menu);
    }

    @Test
    public void insertMenuPermission() {
        Menu menu = menuRepository.getOne(Long.valueOf(10000020));
        Permission permission = permissionRepository.getOne(Long.valueOf(10000033));
        Set<Permission> permissions = new HashSet<Permission>();
        permissions.add(permission);
        menu.setMenuPermissions(permissions);
        menuRepository.save(menu);
    }

    @Test
    public void getPermission() {
        Menu menu = menuRepository.getOne(Long.valueOf(10000020));
        Set<Permission> permissionSet = menu.getMenuPermissions();
        Iterator<Permission> permissionIterator = permissionSet.iterator();
        while (permissionIterator.hasNext()) {
            Permission permission = permissionIterator.next();
            Set<Service> serviceSet = permission.getServices();

        }
//        for (Permission permission : per)
    }
}
