package com.lambda.code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @Author:zhuzhou
 * @Date: 2019/8/29---14:43
 **/
public class TestLambda2 {

    public static void main(String[] args){
        I1 a1 = new I1(){
            public void test(A a){
                a.method();
            }
        };

        I1 a2 = (A a)->a.method();
        I1 a3 = A::method;

        I2 a4 = (A a , String b)->a.method(b);
        I2 a5 = A::method;

        I3 a6 = (A a , String b , String c)->a.method(b, c);
        I3 a7 = A::method;

        I4 a8 = (String b , A a )->a.method(b);

        I5 a9 = s->Integer.parseInt(s);
        I5 a10 = Integer::parseInt;

        I5 a11 = s->System.out.println(s);
        I5 a12 = System.out::println;

        List<String> list = Arrays.asList("ABC","DEF");
        list.forEach(new Consumer<String>(){
            public void accept(String s){
                System.out.println(s);
            }
        });
        list.forEach(s->System.out.println(s));
        list.forEach(System.out::println);
    }
}
interface I1{
    void test(A a);
}
interface I2{
    void test(A a , String b);
}
interface I3{
    void test(A a , String b , String c );
}
interface I4{
    void test(String b , A a);
}
interface I5{
    void test(String s);
}
class A{
    public void method(){
        System.out.println("method()");
    }
    public void method(String s){
        System.out.println("method String");
    }
    public void method(String s1, String s2){
        System.out.println("method String String");
    }

}


