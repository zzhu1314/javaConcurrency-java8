package com.lambda.code;

import java.util.List;
import java.util.Optional;

/**
 * @Author:zhuzhou
 * @Date: 2019/8/29---15:25
 **/
public class TestStream_endOperation {
    public static void main(String[] args) {
        List<Employee> list = Employee.createEmployee();

        long size = list.stream().count();
        System.out.println(size);

        Optional<Employee> o = list.stream().findFirst();
        System.out.println(o.get());

        Optional<Employee> o2 = list.stream().findAny();
        System.out.println(o2.get());

        boolean b1 = list.stream().allMatch(e->e.getAge()>18);
        boolean b2 = list.stream().anyMatch(e->e.getAge()>18);
        boolean b3 = list.stream().noneMatch(e->e.getAge()<6);
        System.out.println(b1);
        System.out.println(b2);
        System.out.println(b3);

        list.stream().forEach(System.out::println);

    }
}
