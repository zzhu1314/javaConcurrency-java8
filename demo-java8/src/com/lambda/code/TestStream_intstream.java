package com.lambda.code;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

/**
 * @Author:zhuzhou
 * @Date: 2019/8/29---15:42
 **/
public class TestStream_intstream {
    public static void main(String[] args) {

		/*
		List<Employee> list = Employee.createEmployee();
		Stream<Employee> stream1 = list.stream();
		//Stream<Integer> stream2 = stream1.map(Employee::getAge);
		IntStream stream2 = stream1.mapToInt(Employee::getAge);
		*/

		/*
		IntStream is = IntStream.range(1, 100);  // 1-99        [1,100)
		System.out.println(is.sum());
		IntStream is2 = IntStream.rangeClosed(1, 100); //1-100  [1,100]
		System.out.println(is2.sum());

		Predicate<Integer> p1 = new Predicate<Integer>(){
			public boolean test(Integer n){
				for(int i = 2 ; i <= Math.sqrt(n);i++){
					if (n % i == 0) return false;
				}
				return true;
			}
		};
		Predicate<Integer> p2 = n->{
			IntStream ist = IntStream.rangeClosed(2, (int)Math.sqrt(n));
			return ist.noneMatch(i->n%i==0);
		};

		System.out.println(p2.test(100));
		System.out.println(p2.test(101));
		*/
        int x = 8000;
        IntStream stream = IntStream.iterate(2, i->i+1);
        int result = stream.filter(n->{
            IntStream ist = IntStream.rangeClosed(2, (int)Math.sqrt(n));
            return ist.noneMatch(i->n%i==0);
        }).skip(x-1).findFirst().getAsInt();

        System.out.println(result);

        DoubleStream stream2 = DoubleStream.generate(Math::random);

    }
}
