package com.sample.configcenter.repository;

import com.sample.configcenter.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 16-11-1.
 */
@Component
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
