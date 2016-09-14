package com.sample.test.user.repository;

import com.sample.test.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by andongxu on 16-6-6.
 */
@Repository
public interface UserRepository extends JpaRepository <User, Long>{

}
