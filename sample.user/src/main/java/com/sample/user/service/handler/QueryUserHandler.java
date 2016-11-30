package com.sample.user.service.handler;

import com.sample.core.Constant;
import com.sample.core.exception.UnifiedException;
import com.sample.core.model.dto.GenericReq;
import com.sample.core.model.dto.GenericSetRsp;
import com.sample.core.service.Service;
import com.sample.core.service.handler.AbstractServiceHandler;
import com.sample.user.dto.QueryUserDto;
import com.sample.user.dto.UserDto;
import com.sample.user.model.User;
import com.sample.user.repository.UserRepository;
import com.sample.user.service.QueryUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by andongxu on 16-11-18.
 */
@Component
public class QueryUserHandler extends AbstractServiceHandler<GenericReq<QueryUserDto>, GenericSetRsp<UserDto>> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean support(Object... objs) {
        Service service = (Service)objs[1];
        if (service.code().equals(QueryUserService.class.getAnnotation(Service.class).code())) {
            return true;
        }
        return false;
    }

    @Override
    public GenericSetRsp<UserDto> doHandle(GenericReq<QueryUserDto> queryUserReqDtoGenericReq, Service service) throws UnifiedException {
        User user = new User();
        BeanUtils.copyProperties(queryUserReqDtoGenericReq.getData(), user);
        Example<User> example = Example.of(user);
        List<User> users = userRepository.findAll(example);
        Set<UserDto> userDtos = new HashSet<UserDto>();
        for (User u : users) {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(u, userDto);
            userDtos.add(userDto);
        }
        GenericSetRsp<UserDto> genericSetRsp = new GenericSetRsp<UserDto>();
        genericSetRsp.setData(userDtos);
        genericSetRsp.setSuccess(true);
        genericSetRsp.setErrorMsg(Constant.SUCCESS[1]);
        return genericSetRsp;
    }
}
