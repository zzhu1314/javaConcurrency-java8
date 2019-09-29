package com.lambda;

import javax.annotation.PreDestroy;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author:zhuzhou
 * @Date: 2019/8/14---11:29
 **/
public class AppleTest {
    public static void main(String[] args) {
        List<Apple> apples = Arrays.asList(new Apple("red", 120), new Apple("green", 130), new Apple("blue", 132));
        //匿名函数的写法
        List<Apple> blue = collect(apples, new Predicate<Apple>() {
            @Override
            public boolean test(Apple apple) {
                if(apple.getColor().equals("blue")){
                    return true;
                }
                return false;
            }
        });
        System.out.println(blue);
        //lambda写法
        collect(apples,(apple)->apple.getColor().equals("green"));
        //排序的列子 匿名函数
        /*Collections.sort(apples, new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight()-o2.getWeight();
            }
        });*/
        //lambda
        //Collections.sort(apples, Comparator.comparingInt(Apple::getWeight))
        apples.sort(Comparator.comparing(Apple::getColor));
        System.out.println(apples);

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        executorService.execute(new Thread(()-> System.out.println("123")));
    }



    public static <T> List<T> collect(List<T> list, Predicate<T> predicate){
        List<T> lists = new ArrayList<>();
        for (T t : list) {
            if(predicate.test(t)){
                lists.add(t);
            }
        }
        return lists;
    }
}
