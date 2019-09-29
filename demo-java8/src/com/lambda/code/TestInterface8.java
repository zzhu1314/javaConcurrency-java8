package com.lambda.code;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

/**
 * @Author:zhuzhou
 * @Date: 2019/8/29---14:33
 **/
public class TestInterface8 {
    public static void main(String[] args) {
        Animal a = Animal.createDog();

        Comparator<String> c1 = Comparator.naturalOrder();
        Comparator<String> c2 = c1.reversed();

        List<String> list = Arrays.asList("Liucy","Huxz","Liangb","Suns");
        list.sort(c2);
        list.forEach(new Consumer<String>(){
            public void accept(String s){
                System.out.println(s);
            }
        });

        Impl i= new Impl();
        i.m2();
    }

}
interface Animal{
    void eat();
    static Animal createDog(){
        class Dog implements Animal{
            public void eat(){
                System.out.println("Dog eat");
            }
        }
        return new Dog();
    }
    static Animal createCat(){
        class Cat implements Animal{
            public void eat(){
                System.out.println("Cat eat");
            }
        }
        return new Cat();
    }
}
interface IA{
    void m1();
    default void m2(){
        System.out.println("A m2");
    }
}
interface IB{
    default void m2(){
        System.out.println("B m2");
    }
}
class Impl implements IA , IB{
    public void m1(){}
    public void m2(){
        IA.super.m2();
        IB.super.m2();
    }
}
