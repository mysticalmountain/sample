package com.sample.configcenter.repository;

import com.sample.configcenter.model.Version;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 16-11-1.
 */
@Component
public interface VersionRepository extends JpaRepository<Version, Long> {
}
