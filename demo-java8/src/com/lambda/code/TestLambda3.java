package com.lambda.code;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @Author:zhuzhou
 * @Date: 2019/8/29---14:58
 **/
public class TestLambda3 {
    public static void main(String[] args) {
		/*
		W1 s1 = new W1(){
			public Worker method(){
				return new Worker();
			}
		};
		W1 s2 = ()->new Worker();
		W1 s3 = Worker::new;
		s3.method();

		W2 s4 = (s)->new Worker(s);
		W2 s5 = Worker::new;
		s5.method("ABC");

		W3 s6 = (s,a)->new Worker(s,a);
		W3 s7 = Worker::new;
		*/
        //Supplier<Worker> s1 = ()->new Worker();
        Supplier<Worker> s1 = Worker::new;
        Worker worker1 = s1.get();

        Function<String,Worker> s2 = Worker::new;
        Worker worker2 = s2.apply("ABC");

        BiFunction<String,Integer,Worker> s3 = Worker::new;
        Worker worker3 = s3.apply("XYZ", 30);
    }
}
interface W1{
    Worker method();
}
interface W2{
    Worker method(String s);
}
interface W3{
    Worker method(String s, int a);
}

class Worker{
    public Worker(){
        System.out.println("Worker()");
    }
    public Worker(String s){
        System.out.println("Worker String");
    }
    public Worker(String s,int a){
        System.out.println("Worker String int" );
    }
}
