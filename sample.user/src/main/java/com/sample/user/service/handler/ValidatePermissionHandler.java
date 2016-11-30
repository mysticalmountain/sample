package com.sample.user.service.handler;

import com.sample.core.Constant;
import com.sample.core.exception.ExceptionLevel;
import com.sample.core.exception.UnifiedException;
import com.sample.core.log.Log;
import com.sample.core.model.dto.Req;
import com.sample.core.model.dto.Rsp;
import com.sample.core.service.Service;
import com.sample.core.service.handler.AbstractBeforeServiceHandler;
import com.sample.core.service.handler.Priority;
import com.sample.permission.model.Permission;
import com.sample.permission.model.Resource;
import com.sample.permission.model.Role;
import com.sample.permission.repository.ServiceRepository;
import com.sample.user.model.User;
import com.sample.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by andongxu on 16-11-14.
 */
//@Priority(15)
//@Component
public class ValidatePermissionHandler<I, O> extends AbstractBeforeServiceHandler<Req, Rsp> {

    private Log log = Log.getLog(this.getClass());

    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean support(Object... objs) {
        Service service = (Service) objs[1];
        if (service != null) {
            return true;
        }
        return false;
    }

    @Override
    public Rsp doHandle(Req req, Service service) throws UnifiedException {
        if (req.getUserId() != null) {
            com.sample.permission.model.Service s = serviceRepository.findByCode(service.code());
            if (s == null) {
                return null;
            }
            Resource resource = s.getResource();
            User user = userRepository.findOne(Long.valueOf(req.getUserId()));
            Set<Role> roles = user.getRoles();
            boolean hasPermission = false;
            for (Role role : roles) {
                Set<Permission> permissions = role.getPermissions();
                for (Permission permission : permissions) {
                    if (resource.getId() == permission.getResource().getId()) {
                       hasPermission = true;
                        break;
                    }
                }
            }
            if (hasPermission) {
                return null;
            } else {
                throw new UnifiedException(ExceptionLevel.COMMON, Constant.EXCEPTION_NO_PERMISSION[0], Constant.EXCEPTION_NO_PERMISSION[1], null, null, null);
            }
        } else if (req.getChannelId() != null) {
            throw new UnifiedException(ExceptionLevel.COMMON, Constant.EXCEPTION_NO_PERMISSION[0], Constant.EXCEPTION_NO_PERMISSION[1], null, null, null);
        } else {
            com.sample.permission.model.Service s = serviceRepository.findByCode(service.code());
            if (s == null) {
                return null;
            } else {
                //TODO 比较用户角色/渠道是否有该服务的权限
                throw new UnifiedException(ExceptionLevel.COMMON, Constant.EXCEPTION_NO_PERMISSION[0], Constant.EXCEPTION_NO_PERMISSION[1], null, null, null);
            }
        }
    }
}
