package com.cigit;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:zhuzhou
 * @Date: 2020/4/7  15:44
 * <? extends T> 和<? super T>的区别
 **/
public class BigCat extends Cat {
    public static void main(String[] args) {
        //类关系：Object >Animal>Cat>BigCat
        //儿子只能拿不能存，父亲可以存,但存只能存自己和自己的儿子，获取时不知道存的是啥？
        //儿子赋值时，上限就是自己，父亲赋值时，下限就是自己
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal());
        List<Cat> cats = new ArrayList<>();
        cats.add(new Cat());
        List<BigCat> bigCats = new ArrayList<>();
        bigCats.add(new BigCat());
        List<? extends Cat> extendsToCat  = bigCats;//<? extends T> 只允许赋值T或T的子类，上界为T
        extendsToCat.add(new Cat());//<? extends> 不允许进行add，适用于get的场景
        List<? super Cat> superToCat  = animals;//<? super T> 只允许赋值T或T的父类，下界为T
        superToCat.add(new Cat());
        superToCat.add(new BigCat());//<? super T> 的 add只允许添加 T或T的子类
        Object object = superToCat.get(0);//get后由于泛型擦除，是不知道返回类型的
    }
}
