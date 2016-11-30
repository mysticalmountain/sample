package com.sample.user.service.handler;

import com.sample.core.exception.UnifiedException;
import com.sample.core.model.dto.GenericReq;
import com.sample.core.model.dto.Rsp;
import com.sample.core.service.ISampleService;
import com.sample.user.BaseTest;
import com.sample.user.dto.AuthorityDto;
import com.sample.user.dto.ProfileDto;
import com.sample.user.dto.UserDto;
import com.sample.user.model.Profile;
import com.sample.user.model.User;
import com.sample.user.model.enums.UserType;
import com.sample.user.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by andongxu on 16-11-19.
 */
public class EditUserHandlerTest extends BaseTest {

    @Autowired
    @Qualifier("editUserService")
    private ISampleService<GenericReq<UserDto>, Rsp> sampleService;
    @Autowired
    private UserRepository userRepository;


    @Test
    public void saveUser() throws UnifiedException {
        UserDto userDto = new UserDto();
        userDto.setName("qian");
        userDto.setUsername("qian");
        userDto.setUserType(UserType.PERSONAL);
        GenericReq<UserDto> genericReq = new GenericReq<UserDto>();
        genericReq.setData(userDto);
        Rsp rsp = sampleService.service(genericReq);
        Assert.assertNotNull(rsp);
    }

    @Test
    public void saveUser2() throws UnifiedException {
        UserDto userDto = new UserDto();
        userDto.setName("zheng11");
        userDto.setUsername("zheng11");
        userDto.setUserType(UserType.PERSONAL);
        ProfileDto profileDto = new ProfileDto();
        profileDto.setAddress("hebei");
        userDto.setProfileDto(profileDto);
        AuthorityDto authorityDto = new AuthorityDto();
        userDto.setAuthorityDto(authorityDto);
        GenericReq<UserDto> genericReq = new GenericReq<UserDto>();
        genericReq.setData(userDto);
        Rsp rsp = sampleService.service(genericReq);
        Assert.assertNotNull(rsp);
    }

    @Test
    public void saveUserAndProfile() throws UnifiedException {
        UserDto userDto = new UserDto();
        userDto.setName("zheng5");
        userDto.setUsername("zheng5");
        userDto.setUserType(UserType.PERSONAL);
        ProfileDto profileDto = new ProfileDto();
        profileDto.setAddress("hebei");
        userDto.setProfileDto(profileDto);
        GenericReq<UserDto> genericReq = new GenericReq<UserDto>();
        genericReq.setData(userDto);
        Rsp rsp = sampleService.service(genericReq);
        Assert.assertNotNull(rsp);
    }


    @Test
    public void update() throws UnifiedException {
        User user = userRepository.findByUsername("qian");
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user , userDto);
        userDto.setName("qianname");
        GenericReq<UserDto> genericReq = new GenericReq<UserDto>();
        genericReq.setData(userDto);
        Rsp rsp = sampleService.service(genericReq);
        Assert.assertNotNull(rsp);
    }
}
