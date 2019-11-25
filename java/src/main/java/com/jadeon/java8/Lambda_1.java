package com.jadeon.java8;

import com.jadeon.java8.entity.Apple;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import static java.util.stream.Collectors.*;
import static java.util.Comparator.*;


public class Lambda_1 {

    /**
     *  Lambda 表达式
     *  可以把 Lambda 表达式理解为简洁地表示可传递的匿名函数的一种方式，它没有名称， 但是有参数列表、函数主题、返回类型，
     *  可能还有一个可以抛出的异常列表。
     *   1、匿名：它不像普通的方法那样有一个明确的名称，写得少而想得多！
     *   2、函数：Lambda函数不像方法那样属于某个特定的类。但和方法一样，Lambda有参数列表、函数主体、返回类型，还可能有可以
     *          抛出异常的列表。
     *   3、传递：Lambda表达式可以作为参数传递给方法或存储在变量中。
     *   4、简洁：无需像匿名类那样写很多模板代码。
     *   基本语法：
     *   (parameters) -> expression
     *   或者(注意语句的花括号)
     *   (parameters) -> {statements;}
     */
    void explain(){

        Comparator<Apple> weights =
//                参数：Apple a1, Apple a2
                (Apple a1, Apple a2)
//                        箭头：->
                        ->
//                        Lambda主体： a1.getWeight().compareTo(a2.getWeight())
                        a1.getWeight().compareTo(a2.getWeight());


       List<Apple> list = Arrays.asList(new Apple("China",100L,"green")
               ,new Apple("Canada", 300L,"red"));

       List<Apple> apples = list.stream().
               filter(
                       (Apple a)
                               ->
                               "green".equals(a.getColor()))
               .collect(toList());

       list.sort(comparing(Apple::getWeight));

    }

}
