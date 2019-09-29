package com.lambda.code;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author:zhuzhou
 * @Date: 2019/8/29---15:46
 **/
public class TestStream_flatMap {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("ABC","DEF","XYZ","ADG","BCR","XYA");
        Stream<String[]> stream2= list.stream().map(s->s.split(""));
        Stream<String> stream3 = stream2.flatMap(Arrays::stream);
        Stream<String> stream4 = stream3.distinct();
        List<String> result = stream4.collect(Collectors.toList());
        result.forEach(System.out::println);

    }
}
