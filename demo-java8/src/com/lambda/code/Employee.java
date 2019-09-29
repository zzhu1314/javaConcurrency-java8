package com.lambda.code;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:zhuzhou
 * @Date: 2019/8/29---14:29
 **/
public class Employee {
    String name;
    int age;
    double salary;
    String dept;

    public Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public Employee(String name, int age, double salary, String dept) {
        super();
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.dept = dept;
    }
    @Override
    public String toString() {
        return "Employee [name=" + name + ", age=" + age + ", salary=" + salary
                + ", dept=" + dept + "]";
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public double getSalary() {
        return salary;
    }
    public String getDept() {
        return dept;
    }

    public static List<Employee> createEmployee(){
        List<Employee> list = new ArrayList<>();
        list.add(new Employee("胡鑫喆" , 25, 30000, "教学"));
        list.add(new Employee("刘春阳" , 40, 8000, "教学"));
        list.add(new Employee("孙帅" , 35, 20000, "研发"));
        list.add(new Employee("杨冬冬" , 20, 5000, "教学"));
        list.add(new Employee("梁彬" , 27, 6500, "教学管理"));
        list.add(new Employee("王宇希" , 23, 9000, "研发"));
        list.add(new Employee("马静" ,26 , 10000, "教学管理"));
        list.add(new Employee("陆小伟" , 18,11000 , "教学"));
        list.add(new Employee("郑春光" ,21 ,3000 , "教学"));
        list.add(new Employee("杨艳玲" , 32, 4000, "教学"));
        list.add(new Employee("姚继科" , 39,8216 , "渠道"));
        list.add(new Employee("张奇" ,51 , 12000, "渠道"));
        list.add(new Employee("陈美霞" , 29,7800 , "渠道"));
        list.add(new Employee("韦柯" ,16 ,1000 , "渠道"));
        list.add(new Employee("岳浩" , 17, 1500, "研发"));
        list.add(new Employee("滕孟春" , 22, 9001, "教学管理"));
        list.add(new Employee("毕龙龙" , 45, 4600, "研发"));
        list.add(new Employee("刘浩" ,12 , 500, "研发"));
        return list;
    }
}
