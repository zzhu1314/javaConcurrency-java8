package com.lambda.code;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

/**
 * @Author:zhuzhou
 * @Date: 2019/8/29---14:39
 **/
public class TestLambada {
    public static void main(String[] args) {

        // ()->void
        Thread t = new Thread(new Runnable(){
            public void run(){
                System.out.println("hello");
            }
        });
        Thread t2 = new Thread(()->System.out.println("hello"));

        // T->void
        List<String> list = Arrays.asList("A","BB","CCC");
        list.forEach(new Consumer<String>(){
            public void accept(String s){
                System.out.println(s);
            }
        });
        list.forEach((s)->System.out.println(s));

        // (T,T)->int
        list.sort(new Comparator<String>(){
            public int compare(String s1 , String s2){
                return s1.length()-s2.length();
            }
        });
        list.sort((s1,s2)->s1.length()-s2.length());
    }
}
