package com.sample.user.repository;

import com.sample.user.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by andongxu on 16-11-16.
 */
@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
