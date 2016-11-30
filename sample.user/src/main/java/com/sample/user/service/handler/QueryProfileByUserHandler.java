package com.sample.user.service.handler;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.sample.core.Constant;
import com.sample.core.exception.UnifiedException;
import com.sample.core.model.dto.GenericReq;
import com.sample.core.model.dto.GenericSetRsp;
import com.sample.core.service.Service;
import com.sample.core.service.handler.AbstractServiceHandler;
import com.sample.user.dto.ProfileDto;
import com.sample.user.dto.QueryProfileByUserDto;
import com.sample.user.dto.UserDto;
import com.sample.user.model.Profile;
import com.sample.user.model.User;
import com.sample.user.repository.ProfileRepository;
import com.sample.user.repository.UserRepository;
import com.sample.user.service.QueryProfileService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by andongxu on 16-11-22.
 */
@Component
public class QueryProfileByUserHandler extends AbstractServiceHandler<GenericReq<QueryProfileByUserDto>, GenericSetRsp<ProfileDto>> {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public boolean support(Object... objs) {
        Service service = (Service)objs[1];
        if (service.code().equals(QueryProfileService.class.getAnnotation(Service.class).code())) {
            GenericReq genericReq = (GenericReq)objs[0];
            if (genericReq.getData() instanceof QueryProfileByUserDto) {
                return true;
            }
        }
        return false;
    }

    @Override
    public GenericSetRsp<ProfileDto> doHandle(GenericReq<QueryProfileByUserDto> genericReq, Service service) throws UnifiedException {
        QueryProfileByUserDto profileDto = genericReq.getData();
        User user = userRepository.findOne(profileDto.getUserId());
        Profile profile = user.getProfile();
        Set<ProfileDto> profileDtoSet = new HashSet<ProfileDto>();
        ProfileDto profileDto1 = new ProfileDto();
        BeanUtils.copyProperties(profile, profileDto1);
        profileDtoSet.add(profileDto1);
        GenericSetRsp<ProfileDto> genericSetRsp = new GenericSetRsp<ProfileDto>();
        genericSetRsp.setData(profileDtoSet);
        genericSetRsp.setData(profileDtoSet);
        genericSetRsp.setSuccess(true);
        genericSetRsp.setErrorCode(Constant.SUCCESS[0]);
        genericSetRsp.setErrorMsg(Constant.SUCCESS[1]);
        return genericSetRsp;
    }
}
