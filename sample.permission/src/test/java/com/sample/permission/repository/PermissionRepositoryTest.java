package com.sample.permission.repository;

import com.sample.permission.BaseTest;
import com.sample.permission.model.Permission;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by andongxu on 16-8-25.
 */
public class PermissionRepositoryTest extends BaseTest {

    @Autowired
    private PermissionRepository permissionRepository;

    @Test
    public void insertPermission() {
        Permission permission = new Permission();
        permissionRepository.save(permission);
    }

}
