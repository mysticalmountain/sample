package com.sample.user.service.handler;

import com.sample.core.Constant;
import com.sample.core.exception.UnifiedException;
import com.sample.core.model.dto.GenericReq;
import com.sample.core.model.dto.Rsp;
import com.sample.core.service.Service;
import com.sample.core.service.handler.AbstractServiceHandler;
import com.sample.permission.dto.RoleDto;
import com.sample.permission.model.Role;
import com.sample.permission.repository.RoleRepository;
import com.sample.user.dto.UserDto;
import com.sample.user.model.Authority;
import com.sample.user.model.Profile;
import com.sample.user.model.User;
import com.sample.user.model.enums.AuthType;
import com.sample.user.repository.AuthorityRepository;
import com.sample.user.repository.ProfileRepository;
import com.sample.user.repository.UserRepository;
import com.sample.user.service.EditUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by andongxu on 16-11-18.
 */
@Component
public class EditUserHandler extends AbstractServiceHandler<GenericReq<UserDto>, Rsp> {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public boolean support(Object... objs) {
        Service service = (Service) objs[1];
        if (service.code().equals(EditUserService.class.getAnnotation(Service.class).code())) {
            return true;
        }
        return false;
    }

    @Override
    public Rsp doHandle(GenericReq<UserDto> userDtoGenericReq, Service service) throws UnifiedException {
        UserDto userDto = userDtoGenericReq.getData();
        User user = null;
        if (userDto != null && userDto.getId() != null) {
            user = userRepository.findOne(userDto.getId());
        }
        if (user == null) {
            user = new User();
            BeanUtils.copyProperties(userDto, user);
            user = userRepository.save(user);
        } else {
            BeanUtils.copyProperties(userDto, user);
            user = userRepository.save(user);
        }
        if (userDto.getProfileDto() != null) {
            Profile profile = new Profile();
            BeanUtils.copyProperties(userDto.getProfileDto(), profile);
            profile.setUser(user);
            profileRepository.save(profile);
        }
        if (userDto.getAuthorityDto() != null) {
            Authority authority = new Authority();
            BeanUtils.copyProperties(userDto.getAuthorityDto(), authority);
            authority.setAuthType(AuthType.USERNAME);
            authority.setUser(user);
            authorityRepository.save(authority);
        }
        if (userDto.getRoleDtos() != null) {
            Iterator<RoleDto> roleDtoIterator = userDto.getRoleDtos().iterator();
            Set<Role> roleSet = new HashSet<Role>();
            while (roleDtoIterator.hasNext()) {
                RoleDto roleDto = roleDtoIterator.next();
                Role role = roleRepository.findOne(roleDto.getId());
                roleSet.add(role);
            }
            user.setRoles(roleSet);
            userRepository.save(user);
        }
        Rsp rsp = new Rsp();
        rsp.setErrorMsg(Constant.SUCCESS[1]);
        rsp.setErrorCode(Constant.SUCCESS[0]);
        rsp.setSuccess(true);
        return rsp;
    }
}
