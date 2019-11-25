package com.jadeon.java8;

import com.jadeon.java8.entity.Dish;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.*;
import static java.util.Comparator.*;

public class Stream_1 {

    static List<Dish> menu = new ArrayList<>();

    static {
        menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH)
        );

    }

   public static void Main(){

       List<String> lowCaloricDishesName = menu
                .stream()
//                或者
//                .parallelStream()
//                选出500卡路里一下的菜肴
                .filter(d -> d.getCalories() < 500)
//                按照卡路里排序
                .sorted(comparing(Dish::getCalories))
//                提前菜肴名称
                .map(Dish::getName)
//                将所有名称保存在List中
                .collect(toList());

        System.out.println(lowCaloricDishesName);
//        result:[season fruit, prawns, rice, chicken, salmon]


       List<String> threeHighCaloricDishNames = menu
               .stream()
//               选出热量大于500的菜肴
               .filter(d -> d.getCalories() > 500)
//               获取菜名
               .map(Dish::getName)
//               只选择前三个
               .limit(3)
//               将结果保存到另一个List中
               .collect(toList());

       System.out.println(threeHighCaloricDishNames);
//       result:[pork, beef, french fries]

    }
    
}
