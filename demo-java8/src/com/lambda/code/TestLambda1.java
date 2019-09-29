package com.lambda.code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @Author:zhuzhou
 * @Date: 2019/8/29---14:40
 **/
public class TestLambda1 {

    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
        list.add(new Student("Liucy" , 30, 60.0,"C1"));
        list.add(new Student("Liangb" ,28 , 100.0,"C2"));
        list.add(new Student("Suns" , 35, 70.5,"C1"));
        list.add(new Student("Huxz" , 18, 100.0,"C2"));
        list.add(new Student("Luxw" , 40, 85.0,"C2"));
        list.add(new Student("Yangdd" ,19 ,90.0 ,"C1"));
        list.add(new Student("Wangyx" , 17,65.0 ,"C2"));


        Predicate<Student> f1 = new Predicate<Student>(){
            public boolean test(Student s){


                return s.getAge()<30;
            }
        };
        Predicate<Student> f2 = (Student s)->{return s.getAge()<30;};
        Predicate<Student> f3 = (Student s)->s.getAge()<30;
        Predicate<Student> f4 = (s)->s.getAge()<30;



        List<Student> result = filterObjects(list, (s)->s.getAge()<30);
        List<Student> result2 = filterObjects(list,(s)->s.getClassNumber().equals("C1"));

        filterObjects(list, (s)->s.getScore()>60);
        filterObjects(list, (s)->s.getScore()<60 && s.getClassNumber().equals("C1"));
        for(Student s : result){
            System.out.println(s);
        }

        List<Integer> ls = Arrays.asList(1,2,3,4,5);
        List<Integer> rs = filterObjects(ls, (i)->i%2==0);
        for(Integer a: rs){
            System.out.println(a);
        }
    }

    static <T> List<T> filterObjects(List<T> list , Predicate<T> filter){
        List<T> result = new ArrayList<>();
        for(T s : list){
            if (filter.test(s)) result.add(s);
        }
        return result;
    }

}
/*
@FunctionalInterface
interface Filter<T>{
	boolean test(T s);
}
*/

class Student{
    String name;
    int age;
    double score;
    String classNumber;
    public Student(String name, int age, double score, String classNumber) {
        super();
        this.name = name;
        this.age = age;
        this.score = score;
        this.classNumber = classNumber;
    }
    @Override
    public String toString() {
        return "Student [name=" + name + ", age=" + age + ", score=" + score
                + ", classNumber=" + classNumber + "]";
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public double getScore() {
        return score;
    }
    public String getClassNumber() {
        return classNumber;
    }
}
