package com.lambda.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author:zhuzhou
 * @Date: 2019/8/26---14:33
 **/
public class FlatMapDemo {
    public static void main(String[] args) {
      /*  List<String> list = Arrays.asList("world","hello");
         list.stream().map(e -> e.split(""))
                 .map(e->Arrays.stream(e)) //把每一个元素当成一个单独流处理
                 .distinct()
                 .collect(Collectors.toList());

        List<String> sout = list.stream().
                map(e -> e.split(""))
                .flatMap(e -> Arrays.stream(e)) //将各个扁平化为单个流
                .distinct()
                .collect(Collectors.toList());
        System.out.println(sout);*/
     /* List<Integer> list1 = Arrays.asList(1,2,3);
      List<Integer> list2 = Arrays.asList(3,4);
      List<int[]> collect = list1.stream().flatMap(i -> list2.stream().map(j -> new int[]{i, j})).collect(Collectors.toList());
        List<int[]> collect1 = list1.stream().flatMap(i ->
                list2.stream().filter(j -> (i + j) % 3 == 0)
                        .map(j -> new int[]{i, j})).collect(Collectors.toList());
        for (int[] ints : collect1) {
            System.out.println(ints[0]);
            System.out.println(ints[1]);
        }*/
     //元素求和
        List<Double> list = Arrays.asList(1.2, 2.4, 3.0, 6.6);
        Double sum = list.stream().mapToDouble(Double::byteValue).sum();
        System.out.println(sum);
    }
}
