package com.jadeon.java8;

import com.jadeon.java8.entity.Apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

public class Lambda_2 {

   static List<Apple> list = new ArrayList<>();

    static {
        list = Arrays.asList(new Apple("China",100L,"green")
                ,new Apple("Canada", 300L,"red")
                ,new Apple("American", 300L,"red"));
    }

    /**
     * 比较复合器
     */
    void explain(){

//      1.逆序(按照重量递减排序)
        list.sort(comparing(Apple::getWeight).reversed());

//      2.比较器链(按照重量递减排序，重量相同时，进一步按国家排序)
        list.sort(comparing(Apple::getWeight).reversed().thenComparing(Apple::getCountry));

    }

}
