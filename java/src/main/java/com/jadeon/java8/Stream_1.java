package com.jadeon.java8;

import com.jadeon.java8.common.Constants;
import com.jadeon.java8.entity.Dish;

import java.util.List;

import static java.util.stream.Collectors.*;
import static java.util.Comparator.*;

public class Stream_1 {


   public static void Main(){

       List<String> lowCaloricDishesName = Constants.menu
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


       List<String> threeHighCaloricDishNames = Constants.menu
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
