package com.sample.user.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.sample.core.exception.UnifiedException;
import com.sample.core.model.dto.GenericReq;
import com.sample.core.model.dto.GenericRsp;
import com.sample.core.model.dto.Rsp;
import com.sample.core.service.ISampleService;
import com.sample.user.dto.LoginReqDto;
import com.sample.user.dto.LoginRspDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by andongxu on 16-11-17.
 */
@Controller
@RequestMapping(value = "/user", produces = "application/json; charset=UTF-8")
public class UserController {

    @Autowired
    @Qualifier("loginService")
    private ISampleService<GenericReq<LoginReqDto>, GenericRsp<LoginRspDto>> loginService;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public @ResponseBody String login(HttpServletRequest request, @RequestBody String body) {
        GenericReq<LoginReqDto> genericReq = JSON.parseObject(body, new TypeReference<GenericReq<LoginReqDto>>(){});
        try {
            GenericRsp<LoginRspDto> genericRsp = loginService.service(genericReq);
            if (genericRsp.isSuccess() == true) {
                request.getSession().setAttribute("userId", genericRsp.getData().getUserId());
            }
            return JSON.toJSONString(genericRsp);
        } catch (UnifiedException e) {
            Rsp rsp = new Rsp();
            rsp.setErrorCode(e.getErrorCode());
            rsp.setErrorMsg(e.getErrorMessage());
            rsp.setSuccess(false);
            return JSON.toJSONString(rsp);
        }
    }
}
