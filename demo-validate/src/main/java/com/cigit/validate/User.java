package com.cigit.validate;

import javax.validation.constraints.NotBlank;

/**
 * @Author:zhuzhou
 * @Date: 2019/11/15  17:25
 **/
public class User {
    @NotBlank(message = "name is empty")
    private  String name;
    @gender(message = "参数不合法")
    private Integer gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                '}';
    }
}
