package com.sample.user.repository;

import com.sample.user.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by andongxu on 16-11-16.
 */
@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long>{

    public Authority findByAccountNo(String accountNo);
}
