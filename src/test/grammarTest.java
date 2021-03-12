package test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 写给自己的提醒事项：
 * 1.写代码之前先捋清思路，然后用注释写上每个步骤
 * 2.LeetCode调试可以在中间部分自己协商"值"
 * 3.题目给的例子都是最简单的，需要自己考虑极端特殊的情况
 * 4.有的科技公司还会让你分析时间复杂度空间复杂度，还会写一个test方法
 * 5.考虑corner case是一个好习惯
 *
 * 用哪种数据结构：栈，堆，队列，数组。用什么方式：迭代，递归，贪心，DP，分治...
 * 是否需要考虑所有组合，如何去除冗余
 *
 * 在30题接触到了新的方法：滑动窗口
 * */
public class grammarTest {

    public static void main(String[] args) {
        System.out.println("Test: "+Math.abs(1 - 0 - 1 - 2));
        char num1 = '8';
        System.out.println(num1);
        int num1_1 = num1;
        System.out.println(num1_1);
        System.out.println(num1-'0');

        //下面没有使用菱形语法指定类型，但是为什么不报错呢
        //因为下面的部分默认为<Object>
        List list1 = new ArrayList();
        list1.add(1);
        Integer numTest = (Integer) list1.get(0);       //取值必须要强制类型转换，所以很麻烦，见Java核心技术P327
        list1.add('a');
        System.out.println(list1);
        System.out.println(list1.getClass().getName().toString());

        System.out.println(62%10);
        System.out.println(('3'-'0')*4);
        System.out.println((char)(56+'0'));
        System.out.println(186%10);
        System.out.println(1%10);

        //用Java8 stream生成自然数
        List<Integer> range = IntStream.rangeClosed(1, 10)
                .boxed().collect(Collectors.toList());
    }
}
