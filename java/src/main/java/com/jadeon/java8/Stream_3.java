package com.jadeon.java8;

import com.jadeon.java8.common.Constants;
import com.jadeon.java8.entity.Dish;
import com.jadeon.java8.entity.Trader;
import com.jadeon.java8.entity.Transaction;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;
import static java.util.Comparator.*;

public class Stream_3 {

    public static void Mian(){

//        找出2018年的所有交易并按交易额排序(从低到高)
       List<Transaction> tr2018 = Constants.transactions.stream()
//               给filter传递一个谓词来选择2018年的交易
                .filter(transaction -> transaction.getYear() == 2018)
//               按照交易额进行排序
                .sorted(comparing(Transaction::getValue))
//               将生成的Stream中的元素收集到一个List中
                .collect(toList());
       System.out.println(tr2018);
//       result: [Transaction(trader=Trader(name=Brian, city=Cambridge), year=2018, value=300), Transaction(trader=Trader(name=Raoul, city=Cambridge), year=2018, value=400)]

//        交易员都在哪些不同的城市工作过
       List<String> cities = Constants.transactions.stream()
//               提取与交易相关的每位交易员所在的城市
                .map(transaction -> transaction.getTrader().getCity())
//               过滤重复的城市
                .distinct()
                .collect(toList());
//               或者使用 代替.distinct().collect(toList());
//                .collect(toSet());
       System.out.println(cities);
//       result: [Cambridge, Milan]

//        获取来自剑桥的交易员，并按姓名排序
       List<Trader> traders = Constants.transactions.stream()
//               从交易中提取所有交易员
                .map(Transaction::getTrader)
//               选择来自剑桥的交易员
                .filter(trader -> "Cambridge".equals(trader.getCity()))
//                过滤重复数据
               .distinct()
//               按照姓名排序
                .sorted(comparing(Trader::getName))
                .collect(toList());
       System.out.println(traders);
//       result: [Trader(name=Alan, city=Cambridge), Trader(name=Brian, city=Cambridge), Trader(name=Raoul, city=Cambridge)]

//        返回所有交易员的姓名字符串，按字母顺序排序
       String traderStr = Constants.transactions.stream()
//               提取所有交易员，得到一个Strings构成的Stream
                .map(transcation -> transcation.getTrader().getName())
//               只选择不同的姓名
                .distinct()
//               排序
                .sorted()
//               逐个拼接姓名，得到一个将所有名字连接起来的String
//                .reduce("", (n1, n2) -> n1 +n2);
//               或者这样
                .collect(joining());
       System.out.println(traderStr);
//       result：AlanBrianMarioRaoul

//        查看有没有来自米兰的交易员
       boolean milan = Constants.transactions.stream()
//               传递一个谓词给anyMatch，检查是否有交易员在米兰工作
                .anyMatch(transcation -> transcation.getTrader().getName().equals("Milan"));
       System.out.println(milan);
//       result: false

//        输出生活在剑桥的交易员的所有交易金额
        Constants.transactions.stream()
//                选择住在剑桥的交易员所进行的交易
                .filter(transcation -> transcation.getTrader().getCity().equals("Cambridge"))
//                提取交易额
                .map(Transaction::getValue)
//                打印每个值
                .forEach(System.out::println);
//        result: 300 1000 400 950

//        所有交易中，最高的交易额是多少
       Optional<Integer> max = Constants.transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);
        System.out.println(max.get());
//        result: 1000

//        找到交易额最小的交易
        Optional<Transaction> min = Constants.transactions.stream()
                .min(comparing(Transaction::getValue));
        System.out.println(min.get());
//        result: Transaction(trader=Trader(name=Brian, city=Cambridge), year=2018, value=300)


//        数值流
//        计算所有菜单的热量：
      int calories = Constants.menu.stream()
               .map(Dish::getCalories)
               .reduce(0, Integer::sum);
        System.out.println(calories);
//        result: 4200
//        或者这样
        calories = Constants.menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();
        System.out.println(calories);
//        result: 4200

//        找到IntStream中的最大元素
        OptionalInt omax = Constants.menu.stream()
                .mapToInt(Dish::getCalories)
                .max();
        System.out.println(omax.getAsInt());
//        result: 800
//        如果没有最大值的话，可以显式处理OptionalInt去定义一个默认值：
       int mx = omax.orElse(1);
        System.out.println(mx);
//        result: 800


//        数值范围：
//        一个从1到100的 偶数流
       IntStream evenNumbers = IntStream.rangeClosed(1, 100)
                .filter(i -> i % 2 == 0);
        System.out.println(evenNumbers.count());
//        result: 50
    }

}
