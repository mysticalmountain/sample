package com.sample.permission.service.handler;

import com.sample.core.exception.UnifiedException;
import com.sample.core.exception.UnifiedExceptionUtil;
import com.sample.core.service.handler.AbstractServiceHandler;
import com.sample.permission.dto.ValidateReq;
import com.sample.permission.dto.ValidateRsp;
import com.sample.permission.model.Menu;
import com.sample.permission.model.Permission;
import com.sample.permission.model.PermissionType;
import com.sample.permission.model.Service;
import com.sample.permission.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by andongxu on 16-8-31.
 */
@Component
public class ValidateMenuHandler extends AbstractServiceHandler<ValidateReq, ValidateRsp> {

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public ValidateRsp doHandle(ValidateReq validateReq, com.sample.core.service.Service service) throws UnifiedException {
        Menu menu = menuRepository.findOne(Long.valueOf(validateReq.getOwner()));
        if (menu == null) {
            UnifiedExceptionUtil.throwSlightException("999999", "菜单不存在", "permission", null, null);
        }
        Set<Permission> permissionSet = menu.getMenuPermissions();
        Iterator<Permission> permissionIterator = permissionSet.iterator();
        boolean isSuccess = false;
        while (permissionIterator.hasNext()) {
            Permission permission = permissionIterator.next();
            Set<Service> serviceSet = permission.getServices();
            Iterator<Service> serviceIterator = serviceSet.iterator();
            while (serviceIterator.hasNext()) {
                Service s = serviceIterator.next();
                if (s.getCode().equals(validateReq.getServiceCode())) {
                    isSuccess = true;
                }
            }

        }
        return new ValidateRsp(isSuccess);
    }

    @Override
    public boolean support(Object o) {
        if (o instanceof ValidateReq) {
            if (((ValidateReq) o).getPermissionType() == PermissionType.MENU) {
                return true;
            }
        }
        return false;
    }
}
