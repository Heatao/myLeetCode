package hard;

import java.util.Arrays;

/**
 * https://www.cnblogs.com/grandyang/p/4318500.html
 * 这道题定义了一种搅乱字符串，就是说假如把一个字符串当做一个二叉树的根，
 * 然后它的非空子字符串是它的子节点，然后交换某个子字符串的两个子节点，重新爬行回去形成一个新的字符串，
 * 这个新字符串和原来的字符串互为搅乱字符串。
 * 这道题可以用递归 Recursion 或是动态规划 Dynamic Programming 来做，我们先来看递归的解法，参见网友 uniEagle 的博客，
 * 简单的说，就是 s1 和 s2 是 scramble 的话，那么必然存在一个在 s1 上的长度 l1，将 s1 分成 s11 和 s12 两段，
 * 同样有 s21 和 s22，那么要么 s11 和 s21 是 scramble 的并且 s12 和 s22 是 scramble 的；
 * 要么 s11 和 s22 是 scramble 的并且 s12 和 s21 是 scramble 的。
 * 就拿题目中的例子 rgeat 和 great 来说，rgeat 可分成 rg 和 eat 两段，
 * great 可分成 gr 和 eat 两段，rg 和 gr 是 scrambled 的， eat 和 eat 当然是 scrambled。根据这点，我们可以写出代码如下：
 */
public class IsScramble87 {
    /*
    直觉反应需要枚举所有情况，用回溯考虑呢
    我本来的想法是用s1去变化所有的情况，看是否有一种等于s2

    下面的写法很妙，但是超时了
     */
    public boolean isScramble(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        if (s1.equals(s2)) return true;

        //Java没有内置的字符串排序方法，需要转换为char数组
        char[] str1 = s1.toCharArray();
        Arrays.sort(str1);
        char[] str2 = s2.toCharArray();
        Arrays.sort(str2);
        if (!Arrays.equals(str1, str2)) return false;
        return backtrack(s1, s2);
    }

    private boolean backtrack(String s1, String s2) {
        int n = s1.length();
        for (int i = 1; i < n; i++) {                       //长度为1直接false
            //for循环里面不会返回false，也就是说，即便递归到子函数为false，也会继续循环下去
            String s11 = s1.substring(0, i);
            String s12 = s1.substring(i);
            String s21 = s2.substring(0, i);
            String s22 = s2.substring(i);
            //这样分割下去，会有两个完全一样的二叉树
            if (isScramble(s11, s21) && isScramble(s12, s22)) return true;

            //因为s11和s22长度不一样，所以需要重写赋值s22
            //所以下面是必须的，也就是符合「交换两个子字符串」
            s21 = s2.substring(s1.length() - i);
            s22 = s2.substring(0, s1.length() - i);
            if (isScramble(s11, s21) && isScramble(s12, s22)) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String s1 = "great";
        String s2 = "rgeat";
        IsScramble87 isScramble87 = new IsScramble87();
        System.out.println(isScramble87.isScramble(s1, s2));
    }
}
