package com.sample.user.service.handler;

import com.sample.core.exception.UnifiedException;
import com.sample.core.model.dto.GenericReq;
import com.sample.core.model.dto.GenericRsp;
import com.sample.core.service.Service;
import com.sample.core.service.handler.AbstractServiceHandler;
import com.sample.user.dto.LoginReqDto;
import com.sample.user.dto.LoginRspDto;
import com.sample.user.model.Authority;
import com.sample.user.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 16-11-16.
 */
@Component
public class LoginHandler extends AbstractServiceHandler<GenericReq<LoginReqDto>, GenericRsp<LoginRspDto>> {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public boolean support(Object... objs) {
        Service service = (Service) objs[1];
        if (service.code().equals("1040")) {
            return true;
        }
        return false;
    }

    @Override
    public GenericRsp<LoginRspDto> doHandle(GenericReq<LoginReqDto> loginDtoGenericReq, Service service) throws UnifiedException {
        Authority authority = authorityRepository.findByAccountNo(loginDtoGenericReq.getData().getAccountNo());
        boolean isSuccess = false;
        String msg = "success";
        GenericRsp<LoginRspDto> genericRsp = new GenericRsp<LoginRspDto>();
        LoginRspDto loginRspDto = new LoginRspDto();
        if (authority != null) {
            if (authority.getPassword().equals(loginDtoGenericReq.getData().getPassword())) {
                Long userId = authority.getUser().getId();
                loginRspDto.setUserId(String.valueOf(userId));
                isSuccess = true;
            } else {
                isSuccess = false;
                msg = "password error";
            }
        } else {
            msg = "account no not exists";
        }
        genericRsp.setSuccess(isSuccess);
        genericRsp.setErrorMsg(msg);
        genericRsp.setData(loginRspDto);
        return genericRsp;
    }
}
