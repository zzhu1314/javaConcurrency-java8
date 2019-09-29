package com.lambda.code;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.ToIntFunction;

/**
 * @Author:zhuzhou
 * @Date: 2019/8/29---14:30
 **/
public class TestComparator {
    public static void main(String[] args) {
        List<Employee> list = new ArrayList<>();
        list.add(new Employee("Liucy", 30, 5500.0));
        list.add(new Employee("Liangb",28 , 6000.0));
        list.add(new Employee("Suns", 35, 3000.5));
        list.add(new Employee("Huxz", 18, 1E6));
        list.add(new Employee("Yangdd",25 , 5500.0));
        list.add(new Employee("Wangyx", 19 ,6010.0 ));

        //list.sort((e1,e2)->e1.getAge()-e2.getAge());
        Comparator<Employee> c1 = Comparator.comparingInt(new ToIntFunction<Employee>(){
            @Override
            public int applyAsInt(Employee e) {
                return e.getAge();
            }
        });
        Comparator<Employee> c2 = Comparator.comparingInt(e->e.getAge());
        Comparator<Employee> c3 = Comparator.comparingInt(Employee::getAge);
        list.sort(c3);

        list.sort(Comparator.comparingDouble(Employee::getSalary).reversed());
        list.sort(Comparator.comparing(Employee::getName));

        Comparator<Employee> c4 = Comparator.comparingDouble(Employee::getSalary);
        Comparator<Employee> c5 = Comparator.comparingInt(Employee::getAge);
        list.sort(c4.thenComparing(c5));

        list.forEach(System.out::println);
    }

}

