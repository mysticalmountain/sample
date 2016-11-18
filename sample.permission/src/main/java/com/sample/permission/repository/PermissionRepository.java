package com.sample.permission.repository;

import com.sample.permission.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Created by andongxu on 16-11-14.
 */
@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
