package com.lambda.code;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author:zhuzhou
 * @Date: 2019/8/29---15:44
 **/
public class TestStream_map {
    public static void main(String[] args) {
        List<Student2> list = new ArrayList<>();
        list.add(new Student2("Liucy",30));
        list.add(new Student2("Huxz",18));
        list.add(new Student2("Liangb",21));
        list.add(new Student2("Suns",20));

        Stream<Student2> stream = list.stream();
        Stream<Student2> stream2 = stream.filter(s->s.getAge()<23);
//		stream2.map(new Function<Student,String>(){
//			public String apply(Student s){
//				return s.getName();
//			}
//		});
//		stream2.map(s->s.getName());
        Stream<String> stream3 = stream2.map(Student2::getName);
        Stream<String> stream4 = stream3.sorted(Comparator.comparingInt(String::length));
        List<String> result = stream4.collect(Collectors.toList());
        System.out.println("---------------------------------");
        result.forEach(System.out::println);
        List<Student2> collect = list.stream().filter(e -> e.age < 23).sorted(Comparator.comparingInt(s -> s.getName().length())).collect(Collectors.toList());
        collect.forEach(e->System.out.println(e));
    }
}
class Student2{
    String name;
    int age;
    public Student2(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    @Override
    public String toString() {
        return "Student [name=" + name + ", age=" + age + "]";
    }
}
