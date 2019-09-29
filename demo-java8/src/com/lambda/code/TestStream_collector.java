package com.lambda.code;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author:zhuzhou
 * @Date: 2019/8/29---15:00
 **/
public class TestStream_collector {
    public static void main(String[] args) {
        List<Employee> list = Employee.createEmployee();
     	//Long l = list.stream().collect(counting());
//		long l2 = list.stream().count();

//		Set<Employee> set = list.stream().collect(toSet());


        //员工的平均年龄
        double result = list.stream().collect(averagingInt(Employee::getAge));
        //员工的平均工资
        result = list.stream().collect(averagingDouble(Employee::getSalary));

        //年龄最小的员工

        Employee e = list.stream().collect(minBy(comparingInt(Employee::getAge))).get();
        Employee e2 = list.stream().min(comparingInt(Employee::getAge)).get();

        System.out.println(e2);

        //把所有员工的名字拼在一起
        Stream<Employee> stream1 = list.stream();
        Stream<String> stream2 = stream1.map(Employee::getName);
        String total = stream2.collect(joining(","));
        System.out.println(total);
    }
}
