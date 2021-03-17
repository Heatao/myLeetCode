package hard;

import java.util.regex.Pattern;

/**
 * 有效整数很简单:                  "[+-]?\d+"
 *
 * 有效小数基于有效整数，考虑两种格式:
 * '.'之前至少一位数字              "[+-]?\d+\.\d*"
 * '.'之前没有数字，之后至少一位     "[+-]?\.\d+"
 *
 * 有效数字基于有效整数和小数，考虑两种格式:
 * 不存在 "eE", 就是一个小数或整数              "[+-]?(\d+(\.\d*)?|\.\d+)"
 * 存在一个"eE", 前面是小数或整数, 后面是整数    "[+-]?(\d+(\.\d*)?|\.\d+)[eE][+-]?\d+"
 *
 * from https://leetcode-cn.com/problems/valid-number/comments/815694
 * 收获：1.正则匹配中｜是左边或者右边的表达式，不是只和最近的相关 2.正常的一个\，在Java的正则匹配中要写\\
 */
public class IsNumber65 {
    public boolean isNumber(String s) {
        //用字符串.matches和Pattern.matches是一样的
        return s.matches("[+-]?(\\d+(\\.\\d*)?|\\.\\d+)([eE][+-]?\\d+)?");
    }

    public static void main(String[] args) {
        String s = ".1";
        IsNumber65 isNumber65 = new IsNumber65();
        System.out.println(isNumber65.isNumber(s));
    }
}
