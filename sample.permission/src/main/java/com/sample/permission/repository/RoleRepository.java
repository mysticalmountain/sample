package com.sample.permission.repository;

import com.sample.permission.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by andongxu on 16-8-25.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
