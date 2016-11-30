package com.sample.user.service.handler;

import com.sample.core.exception.UnifiedException;
import com.sample.core.model.dto.GenericReq;
import com.sample.core.model.dto.GenericSetRsp;
import com.sample.core.service.ISampleService;
import com.sample.user.BaseTest;
import com.sample.user.dto.ProfileDto;
import com.sample.user.dto.QueryProfileByUserDto;
import com.sample.user.dto.UserDto;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by andongxu on 16-11-22.
 */
public class QueryProfileHandlerTest extends BaseTest {

    @Autowired
    @Qualifier("queryProfileService")
    private ISampleService<GenericReq<QueryProfileByUserDto>, GenericSetRsp<ProfileDto>> sampleService;

    @Test
    public void query() throws UnifiedException {
        QueryProfileByUserDto queryProfileByUserDto = new QueryProfileByUserDto();
        queryProfileByUserDto.setUserId(Long.valueOf(10000049));
        GenericReq<QueryProfileByUserDto> genericReq = new GenericReq<QueryProfileByUserDto>();
        genericReq.setData(queryProfileByUserDto);
        GenericSetRsp<ProfileDto> genericSetRsp = sampleService.service(genericReq);
        Assert.assertNotNull(genericSetRsp);
    }
}
