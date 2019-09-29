package com.lambda.code;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

/**
 * @Author:zhuzhou
 * @Date: 2019/8/29---15:27
 **/
public class TestStream_group {
    public static void main(String[] args) {
        List<Employee> list = Employee.createEmployee();
//		Map<String, List<Employee>> result = list.stream().collect(groupingBy(Employee::getDept));
//
//		result.forEach((s,l)->{
//			System.out.println(s);
//			l.forEach(System.out::println);
//		});

        //每个部门的平均工资
        Map<String,Double> result = list.stream().collect(groupingBy(
                Employee::getDept,
                averagingDouble(Employee::getSalary)
        ));
        result.forEach((s,d)->System.out.println(s+":"+d));

        //每个部门的年龄最大的员工
        Map<String,Employee> result2 = list.stream().collect(groupingBy(Employee::getDept,
                collectingAndThen(
                        maxBy(comparingInt(Employee::getAge)),
                        Optional::get
                )
        ));
        result2.forEach((s,o)->System.out.println(s+":"+o));

        List<String> ss = Arrays.asList("LIUCY","HUXZ","LINAGB");
       /* String s = ss.stream().collect(mapping(
                String::toLowerCase,
                joining(",")
        ));*/
        String s = ss.stream().map(
                e -> e.toLowerCase()).collect(Collectors.joining(","));
        System.out.println(s);
        String s1 = " ssd ";
        s1= s1+"asc";
        System.out.println(s1);
    }
}
