package com.sample.user.service.handler;

import com.sample.core.Constant;
import com.sample.core.exception.UnifiedException;
import com.sample.core.model.dto.GenericSetRsp;
import com.sample.core.model.dto.IdLongReq;
import com.sample.core.service.Service;
import com.sample.core.service.handler.AbstractServiceHandler;
import com.sample.user.dto.AuthorityDto;
import com.sample.user.model.Authority;
import com.sample.user.model.User;
import com.sample.user.repository.UserRepository;
import com.sample.user.service.QueryUserAuthorityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by andongxu on 16-11-23.
 */
@Component
public class QueryUserAuthorityHandler extends AbstractServiceHandler<IdLongReq, GenericSetRsp<AuthorityDto>> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean support(Object... objs) {
        Service service = (Service)objs[1];
        if (service.code().equals(QueryUserAuthorityService.class.getAnnotation(Service.class).code())) {
            return true;
        }
        return false;
    }

    @Override
    public GenericSetRsp<AuthorityDto> doHandle(IdLongReq idLongReq, Service service) throws UnifiedException {
        User user = userRepository.findOne(idLongReq.getId());
        Set<Authority> authoritySet = user.getAuthtications();
        Iterator<Authority> authorityIterator = authoritySet.iterator();
        Set<AuthorityDto> authorityDtoSet = new HashSet<AuthorityDto>();
        while(authorityIterator.hasNext()) {
            Authority authority = authorityIterator.next();
            AuthorityDto authorityDto = new AuthorityDto();
            BeanUtils.copyProperties(authority, authorityDto);
            authorityDtoSet.add(authorityDto);
        }
        GenericSetRsp<AuthorityDto> genericSetRsp = new GenericSetRsp<AuthorityDto>();
        genericSetRsp.setData(authorityDtoSet);
        genericSetRsp.setSuccess(true);
        genericSetRsp.setErrorCode(Constant.SUCCESS[0]);
        genericSetRsp.setErrorMsg(Constant.SUCCESS[1]);
        return genericSetRsp;
    }
}
