package com.sample.permission.service.handler;

import com.sample.core.exception.UnifiedException;
import com.sample.core.exception.UnifiedExceptionUtil;
import com.sample.core.service.Parent;
import com.sample.core.service.handler.AbstractServiceHandler;
import com.sample.core.service.handler.IServiceHandler;
import com.sample.core.service.handler.ServiceHandlerChain;
import com.sample.permission.dto.EditChannelReq;
import com.sample.permission.dto.BaseRsp;
import com.sample.permission.dto.KeyValue;
import com.sample.permission.model.*;
import com.sample.permission.model.Service;
import com.sample.permission.repository.ChannelRepository;
import com.sample.permission.repository.PermissionRepository;
import com.sample.permission.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by andongxu on 16-8-30.
 */
@Component
public class EditChannelHandler extends AbstractServiceHandler<EditChannelReq, BaseRsp> {

    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private ChannelRepository channelRepository;

    @Override
    public boolean support(Object o) {
        if (o instanceof EditChannelReq) {
            return true;
        }
        return false;
    }

    @Override
    public BaseRsp doHandle(EditChannelReq editChannelReq, com.sample.core.service.Service service) throws UnifiedException {
        Channel channel = channelRepository.findByCode(editChannelReq.getOwner());
        if (channel == null) {
            UnifiedExceptionUtil.throwSlightException("999999", "渠道代码不存在", "permission", null, null);
        }
        Set<KeyValue<String, Operate>> keyValues = editChannelReq.getKeyValues();
        Iterator<KeyValue<String, Operate>> keyValueIterator = keyValues.iterator();
        Set<Permission> permissions = new HashSet<Permission>();
        while (keyValueIterator.hasNext()) {
            KeyValue<String, Operate> keyValue = keyValueIterator.next();
            String serviceCode = keyValue.getK();                       //服务代码
            Operate operate = keyValue.getV();                          //操作权限
            Service s = serviceRepository.findByCode(serviceCode);
            if (s == null) {
                UnifiedExceptionUtil.throwSlightException("999999", "服务代码不存在", "permission", null, null);
            }
            Set<Permission> permissions1 = s.getPermissions();
            boolean exist = false;
            //存在权限则提取权限
            for (Permission permission : permissions1) {
                if (permission.getOperate().getValue() == operate.getValue()) {
                    permissions.add(permission);
                    exist = true;
                }
            }
            //不存在这个权限则增加权限
            if (!exist) {
                Permission permission = new Permission();
                permission.setOperate(operate);
                permission.setPermissionType(PermissionType.CHANNEL);
                Set<Service> services = new HashSet<Service>();
                services.add(s);
                permission.setServices(services);
                permission = permissionRepository.save(permission);
                permissions.add(permission);
            }
        }
        channel.setPermissions(permissions);
        channelRepository.save(channel);
        return new BaseRsp(true);
    }

}

