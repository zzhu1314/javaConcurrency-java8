package com.rabbit;

import lombok.ToString;

/**
 * 构造者模式
 * @Author:zhuzhou
 * @Date: 2019/9/12  13:52
 **/
@ToString
public class User {
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    public static Builder builder(){
        return new Builder();
    }
    public static class Builder{
          String name;
           Integer age;
         public Builder name(String name){
             this.name = name;
             return this;
         }
        public Builder age(Integer age){
            this.age = age;
            return this;
        }
        public User build(){
            User user = new User();
            user.setName(this.name);
            user.setAge(this.age);
            return user;
        }
    }

    public static void main(String[] args) {
        User user = User.builder().name("张三").age(12).build();
        System.out.println(user);
    }
}
