package com.rabbit.controller;

import com.rabbit.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author:zhuzhou
 * @Date: 2019/9/9  12:30
 **/
@RestController
public class UserController {

    @Autowired
    @Qualifier("platform-redis")
    private RedisTemplate redisTemplate;
    @RequestMapping(method = RequestMethod.POST,value = "/user")
    public UserDTO test(@RequestBody @Validated UserDTO userDTO){
        List<UserDTO> list = new ArrayList<>();
        Arrays.asList(new UserDTO("张三",12),new UserDTO("wangw",32));
        ListOperations listOperations =
                redisTemplate.opsForList();
        listOperations.leftPush("123","123");
        return userDTO;
    }
}
