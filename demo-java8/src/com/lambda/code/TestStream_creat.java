package com.lambda.code;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @Author:zhuzhou
 * @Date: 2019/8/29---15:23
 **/
public class TestStream_creat {
    public static void main(String[] args) throws Exception{
        Stream<String> stream1 = Stream.of("ABC","DEF","XYZ");
        Stream<String> stream2 = Arrays.stream(new String[]{"ABC","DEF","XYZ"});

        Stream<String> stream3 = Files.lines(Paths.get("a.txt"), Charset.defaultCharset());
        stream3.forEach(System.out::println);
    }
}
