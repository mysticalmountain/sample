package com.sample.configcenter.repository;

import com.sample.configcenter.model.Version;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by andongxu on 16-11-1.
 */
@Component
public interface VersionRepository extends JpaRepository<Version, Long> {

    @Query(value = "select * from Version where project_id = ?1", nativeQuery = true)
    public List<Version> findByProject(String projectId);
}
