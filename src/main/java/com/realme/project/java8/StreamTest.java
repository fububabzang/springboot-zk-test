package com.realme.project.java8;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;


/**
 * @program: springboot-zk-test
 * @description:
 * @author: realme
 * @create: 2020-03-03 10:08
 *
 * java 1.8特性之stream流操作
 *
 * 1; 去重 distinct
 * 2；排序 sorted
 * 3；反向排序 sorted(Comparator.reverseOrder())
 * 4；Collectors 转换成集合  Collectors.toList()  或者聚合 Collectors.joining("")
 * 5；map 可以对stream流中的数据做一对一的映射
 * 6；mapToInt 、mapToLong 、mapToDouble 对流中的数据进行统计
 * 7; filter 对流数据进行过滤操作
 *
 **/

/**
 * java8 特性总结
 * 速度更快 – 红黑树
 * 代码更少 – Lambda
 * 强大的Stream API – Stream
 * 便于并行 – Parallel
 * 最大化减少空指针异常 – Optional
 *
 *
 */

public class StreamTest {

    /** logger */
    private static final Logger logger = LogManager.getLogger(StreamTest.class);

    public static void main(String[] args) {

        /**
         * 排序 sorted
         */

        //去重 由小到大排序 numbers.stream().distinct().sorted().collect(Collectors.toList());
        //去重 由大到小排序 numbers.stream().distinct().sorted(Comparator.reverseOrder()).collect(Collectors.toList());

        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

        IntSummaryStatistics intSummaryStatistics = numbers.stream().mapToInt((a) -> a).summaryStatistics();

        System.out.println(intSummaryStatistics.getMax());

        List<Integer> collect1 = numbers.stream().distinct().sorted(Comparator.reverseOrder()).collect(Collectors.toList());

        System.out.println(collect1);

        /**
         * Collectors
         */
        //Collectors 类实现了很多归约操作，例如将流转换成集合和聚合元素。Collectors 可用于返回列表或字符串：
        List<String> stringList2 = Arrays.asList("eqw", "wer", "erte", "rty", "ertye");

        List<String> wer1 = stringList2.stream().filter(a -> !a.equals("wer")).collect(Collectors.toList());

        String wer2 = stringList2.stream().filter(a -> !a.equals("wer")).collect(Collectors.joining(""));

        System.out.println(wer1);

        System.out.println(wer2);


        /**
         * 并行流 parallelStream
         */

        //parallelStream 是流并行处理程序的代替方法。以下实例我们使用 parallelStream 来输出空字符串的数量：
        List<String> stringList1 = Arrays.asList("eqw", "wer", "erte", "rty", "ertye");

        List<String> wer = stringList1.parallelStream().filter(a -> !a.equals("wer")).collect(Collectors.toList());

        System.out.println(wer);

        /**
         * map
         */
        //map 方法用于映射每个元素到对应的结果，以下代码片段使用 map 输出了元素对应的平方数：

        List<Integer> integerList = Arrays.asList(11, 11 , 12, 15, 18, 19);

        List<Integer> collect = integerList.stream().map(i -> i * i).distinct().collect(Collectors.toList());

        System.out.println(collect);

        /**
         * filter
         */
        //filter 要求过滤掉集合中的"bb"的字段

        List<String> stringList = Arrays.asList("ab", "bb", "cb", "db", " ", "iosudf");

        List<String> bb = stringList.stream().filter(s -> !s.equals("bb")).distinct().collect(Collectors.toList());

        System.out.println(bb);

        //并行流与串行流
        long startTime = System.currentTimeMillis();
        Instant start = Instant.now();


        long sum = LongStream.rangeClosed(0, 1000000000L)
                .parallel()//并行流
                //.sequential()//串行流
                .reduce(0, Long::sum);

        Instant end = Instant.now();
        long endTime = System.currentTimeMillis();
        System.out.println(Duration.between(start,end).toMillis() + "ms");
        System.out.println((endTime - startTime) + "ms");
    }
}
