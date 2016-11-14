package com.sample.configcenter.service.version.handler;

import com.sample.configcenter.BaseTest;
import com.sample.configcenter.dto.version.VersionDto;
import com.sample.core.exception.UnifiedException;
import com.sample.core.model.dto.GenericReq;
import com.sample.core.model.dto.Rsp;
import com.sample.core.service.ISampleService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by andongxu on 16-11-10.
 */
public class SaveVersionHandlerTest extends BaseTest {

    @Autowired
    @Qualifier("saveVersionService")
    private ISampleService<GenericReq<VersionDto>, Rsp> sampleService;

    @Test
    public void success() throws UnifiedException {
        VersionDto versionDto = new VersionDto();
        versionDto.setName("1.0.0");
        versionDto.setContent("snapshot");
        versionDto.setProjectId(Long.valueOf(10000007));
        GenericReq<VersionDto> genericReq = new GenericReq<VersionDto>();
        genericReq.setData(versionDto);
        Rsp rsp = sampleService.service(genericReq);
        Assert.assertNotNull(rsp);
    }
}
