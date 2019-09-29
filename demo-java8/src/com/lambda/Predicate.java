package com.lambda;

/**
 * @Author:zhuzhou
 * @Date: 2019/8/14---11:27
 **/
@FunctionalInterface
public interface Predicate<T> {

      boolean test(T t);
}
