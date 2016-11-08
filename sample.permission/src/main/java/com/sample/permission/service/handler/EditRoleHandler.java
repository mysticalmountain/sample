package com.sample.permission.service.handler;

import com.sample.core.exception.UnifiedException;
import com.sample.core.exception.UnifiedExceptionUtil;
import com.sample.core.service.handler.AbstractServiceHandler;
import com.sample.core.service.handler.IServiceHandler;
import com.sample.permission.dto.BaseRsp;
import com.sample.permission.dto.EditRoleReq;
import com.sample.permission.dto.KeyValue;
import com.sample.permission.model.*;
import com.sample.permission.repository.MenuRepository;
import com.sample.permission.repository.PermissionRepository;
import com.sample.permission.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by andongxu on 16-8-31.
 */
@Component
public class EditRoleHandler extends AbstractServiceHandler<EditRoleReq, BaseRsp> {

    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public boolean support(Object ... objs) {
        if (objs[0] instanceof EditRoleReq) {
            return true;
        }
        return false;
    }

    @Override
    public BaseRsp doHandle(EditRoleReq editRoleReq, com.sample.core.service.Service service) throws UnifiedException {
        Role role = roleRepository.findOne(Long.valueOf(editRoleReq.getOwner()));
        roleRepository.flush();
        if (role.getId() == null) {
            UnifiedExceptionUtil.throwSlightException("999999", "角色不存在", "permission", null, null);
        }
        Set<KeyValue<Long, Operate>> keyValues = editRoleReq.getKeyValues();
        Iterator<KeyValue<Long, Operate>> keyValueIterator = keyValues.iterator();
        Set<Permission> permissions = new HashSet<Permission>();
        while (keyValueIterator.hasNext()) {
            KeyValue<Long, Operate> keyValue = keyValueIterator.next();
            Long menuId = keyValue.getK();                       //服务代码
            Operate operate = keyValue.getV();                          //操作权限
            Menu menu = menuRepository.getOne(menuId);
            if (menu == null) {
                UnifiedExceptionUtil.throwSlightException("999999", "菜单不存在", "permission", null, null);
            }
            Set<Permission> permissions1 = menu.getPermissions();
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
                Set<Menu> menus = new HashSet<Menu>();
                menus.add(menu);
                permission.setMenus(menus);
                permission = permissionRepository.save(permission);
                permissions.add(permission);
            }
        }
        role.setPermissions(permissions);
        roleRepository.save(role);
        return new BaseRsp();
    }
}
