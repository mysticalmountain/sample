package com.sample.configcenter.repository;

import com.sample.configcenter.BaseTest;
import com.sample.configcenter.model.Project;
import com.sample.configcenter.model.Version;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by andongxu on 16-11-10.
 */
public class VersionRepositoryTest extends BaseTest {

    @Autowired
    private VersionRepository versionRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Test
    public void test1() {
        Version version = new Version();
        version.setName("1.0.0");
        version.setContent("snapshot");
        versionRepository.save(version);
    }

    @Test
    public void test2() {
        Project project = projectRepository.findOne(Long.valueOf(10000007));
        Version version = new Version();
        version.setName("1.0.0");
        version.setContent("snapshot");
        version.setProject(project);
        versionRepository.save(version);
    }
}
