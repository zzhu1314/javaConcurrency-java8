package com.cigit.controller;

import com.cigit.validate.User;
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
public class TestController {

    @RequestMapping(value = "/validate",method = RequestMethod.POST)
    public void test(@Validated @RequestBody  User user){
        System.out.println(user);

    }
}
