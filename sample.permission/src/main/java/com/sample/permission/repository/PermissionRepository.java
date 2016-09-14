package com.sample.permission.repository;

import com.sample.permission.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 16-8-25.
 */
@Component
public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
