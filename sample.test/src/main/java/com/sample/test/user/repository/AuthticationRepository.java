package com.sample.test.user.repository;

import com.sample.test.user.domain.Authtication;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by andongxu on 16-6-13.
 */
public interface AuthticationRepository extends JpaRepository<Authtication, Long> {
}
