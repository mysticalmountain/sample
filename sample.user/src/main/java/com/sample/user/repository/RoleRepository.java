package com.sample.user.repository;

import com.sample.user.model.Role;
import com.sample.user.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by andongxu on 16-6-7.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {


}
