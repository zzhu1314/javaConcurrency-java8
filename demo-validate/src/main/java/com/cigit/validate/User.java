package com.cigit.validate;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @Author:zhuzhou
 * @Date: 2019/11/15  17:25
 **/
@Data
public class User {
    @NotBlank(message = "name is empty")
    private  String name;
    @gender(message = "参数不合法")
    private Integer gender;
    @Email
    private String email;
}
