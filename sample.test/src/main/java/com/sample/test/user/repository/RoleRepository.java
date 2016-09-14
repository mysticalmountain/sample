package com.sample.test.user.repository;

import com.sample.test.user.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by andongxu on 16-6-7.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
