package com.sample.configcenter.service.version.handler;

import com.sample.configcenter.BaseTest;
import com.sample.configcenter.dto.version.VersionDto;
import com.sample.core.exception.UnifiedException;
import com.sample.core.model.dto.GenericReq;
import com.sample.core.model.dto.GenericSetRsp;
import com.sample.core.service.ISampleService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by andongxu on 16-11-11.
 */
public class QueryVersionHandlerTest extends BaseTest {

    @Autowired
    @Qualifier("queryVersionService")
    private ISampleService<GenericReq<VersionDto>, GenericSetRsp<VersionDto>> sampleService;

    @Test
    public void succes() throws UnifiedException {
        GenericReq<VersionDto> genericReq = new GenericReq<VersionDto>();
        VersionDto versionDto = new VersionDto();
        versionDto.setProjectId(Long.valueOf(10000007));
        genericReq.setData(versionDto);
        GenericSetRsp<VersionDto> genericSetRsp = sampleService.service(genericReq);
        Assert.assertNotNull(genericSetRsp);
    }
}
