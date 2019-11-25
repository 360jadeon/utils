package com.jadeon.java8;

import com.jadeon.java8.common.Constants;
import com.jadeon.java8.entity.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.*;
import static java.util.Comparator.*;

public class Stream_2 {

    public static void Main(){

      List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 4, 3, 2, 1);
      numbers
              .stream()
//              选出偶数
              .filter(i -> i % 2 == 0)
//              过滤重复元素
              .distinct()
              .forEach(System.out::println);
//      result:[2 4 6]


//     截断流:limit(n)
       List<Dish> dishes = Constants.menu
                .stream()
                .filter(d -> d.getCalories() > 500)
                .limit(3)
                .collect(toList());
       System.out.println(dishes);
//       result:[Dish(name=pork, vegetarian=false, calories=800, type=MEAT), Dish(name=beef, vegetarian=false, calories=700, type=MEAT), Dish(name=french fries, vegetarian=true, calories=530, type=OTHER)]


//      跳过元素：skip(n), limit(n)和skip(n)互补
        dishes = Constants.menu
                .stream()
                .filter(d -> d.getCalories() > 500)
                .skip(3)
                .collect(toList());
        System.out.println(dishes);
//        result: [Dish(name=pizza, vegetarian=true, calories=550, type=OTHER)]


//      映射：map
        List<String> dashNames = Constants.menu
                .stream()
                .map(Dish::getName)
                .collect(toList());
        System.out.println(dashNames);
//        result: [pork, beef, chicken, french fries, rice, season fruit, pizza, prawns, salmon]

        List<String> words = Arrays.asList("Java 8", "Lambdas", "Stream");
        List<Integer> wordsLength = words.stream()
                .map(String::length)
                .collect(toList());
        System.out.println(wordsLength);
//        result: [6, 7, 6]

//        获取每道菜的名称长度
       List<Integer> dishNameLengths = Constants.menu
                .stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(toList());
        System.out.println(dishNameLengths);
//        result: [4, 4, 7, 12, 4, 12, 5, 6, 6]


//     流的扁平化:flatMap
        words = Arrays.asList("Hello", "World");
        List<String> uniqueCharacters =
                words.stream()
//                        将每个单词转换由字母构成的数组
                        .map(w -> w.split(""))
//                        将各个生成流扁平化为单个流
                        .flatMap(Arrays::stream)
                        .distinct()
                        .collect(toList());
        System.out.println(uniqueCharacters);
//      result:[H, e, l, o, W, r, d]



//     求和
       int sum = numbers.stream().reduce(0, Integer::sum);
       System.out.println(sum);
//       result: 31
        Optional<Integer> sums = numbers.stream().reduce((a, b) -> (a + b));
        System.out.println(sums.get());
//        result: 31
        /**
         * Optional简介:
         *  Optional<T>类（java.util.Optional）是一个容器类，代表一个值存在或不存在。在
         *  上面的代码中， findAny可能什么元素都没找到。 Java 8的库设计人员引入了Optional<T>，这
         *  样就不用返回众所周知容易出问题的null了
         */


//     最大值：
       Optional<Integer> max = numbers.stream().reduce(Integer::max);
       System.out.println(max.get());
//       result: 6
//     最小值：
        Optional<Integer> min = numbers.stream().reduce(Integer::min);
        System.out.println(min.get());
//       result: 1


    }

}
