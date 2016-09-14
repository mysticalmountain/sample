package com.sample.test.user.repository;

import com.sample.test.user.domain.AuthorProfile;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by andongxu on 16-6-14.
 */
public interface AuthorProfileRepository extends JpaRepository<AuthorProfile, Long> {
}
