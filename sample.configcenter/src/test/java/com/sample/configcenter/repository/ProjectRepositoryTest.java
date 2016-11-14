package com.sample.configcenter.repository;

import com.sample.configcenter.BaseTest;
import com.sample.configcenter.model.Project;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by andongxu on 16-11-1.
 */
public class ProjectRepositoryTest extends BaseTest {

    @Autowired
    private ProjectRepository projectRepository;

    @Test
    public void save1() {
        Project project = new Project();
        project.setName("net bank");
        project.setContent("the bank of network");
        projectRepository.save(project);
    }


}
