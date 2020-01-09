package com.cigit.controller;

import com.cigit.validate.User;
import com.cigit.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:zhuzhou
 * @Date: 2019/11/15  17:27
 **/
@RestController
@Slf4j
public class TestController {

    @RequestMapping(value = "/validate",method = RequestMethod.POST)
    public Object test(@Validated @RequestBody  User user, BindingResult bindingResult){
        log.debug("用户详情{}",user);
        return new Response(200,"SUCCESS");
    }
}
