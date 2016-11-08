package com.sample.permission.service.handler;

import com.sample.core.exception.UnifiedException;
import com.sample.core.exception.UnifiedExceptionUtil;
import com.sample.core.service.handler.AbstractServiceHandler;
import com.sample.permission.dto.ValidateReq;
import com.sample.permission.dto.ValidateRsp;
import com.sample.permission.model.Channel;
import com.sample.permission.model.Permission;
import com.sample.permission.model.PermissionType;
import com.sample.permission.model.Service;
import com.sample.permission.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by andongxu on 16-8-31.
 */
@Component
public class ValidateChannelHandler extends AbstractServiceHandler<ValidateReq, ValidateRsp> {

    @Autowired
    private ChannelRepository channelRepository;

    @Override
    public ValidateRsp doHandle(ValidateReq validateReq, com.sample.core.service.Service service) throws UnifiedException {
        Channel channel = channelRepository.findByCode(validateReq.getOwner());
        if (channel == null) {
            UnifiedExceptionUtil.throwSlightException("999999", "渠道代码不存在", "permission", null, null);
        }
        Set<Permission> permissions = channel.getPermissions();
        boolean isSuccess = false;
        for (Permission permission : permissions) {
            Set<Service> services = permission.getServices();
            for (Service s: services) {
                if (s.getCode().equals(validateReq.getServiceCode())) {
                    isSuccess = true;
                }
            }
        }
        return new ValidateRsp(isSuccess);
    }

    @Override
    public boolean support(Object ... objs) {
        if (objs[0] instanceof ValidateReq) {
            if (((ValidateReq) objs[0]).getPermissionType() == PermissionType.CHANNEL) {
                return true;
            }
        }
        return false;
    }
}
