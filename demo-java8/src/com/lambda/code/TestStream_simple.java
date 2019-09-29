package com.lambda.code;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author:zhuzhou
 * @Date: 2019/8/29---15:43
 **/
public class TestStream_simple {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Liucy","Huxz","Suns","Liangb","Yangdd","Wangyx","Huxz","Suns");
//		Stream<String> stream = list.stream();
//		Stream<String> stream2 = stream.filter(s->s.length()==4);
//		Stream<String> stream3 = stream2.distinct();
//		List<String> result = stream3.collect(toList());

        //List<String> result = list.stream().filter(s->s.length()==4).distinct().collect(Collectors.toList());
        //result.forEach(System.out::println);
        String collect = list.stream().collect(Collectors.joining(","));
        System.out.println(collect);
    }
}
