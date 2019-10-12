package com.rabbit.dto;



/**
 * @Author:zhuzhou
 * @Date: 2019/9/9  12:28
 **/
public class UserDTO {


    private String userName;


    private Integer age;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public UserDTO(String userName, Integer age) {
        this.userName = userName;
        this.age = age;
    }

    public UserDTO() {
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}
