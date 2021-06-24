package CodingInterviews;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 质因子：（或质因数）在数论里是指能整除给定正整数的质数。
 */
public class Offer49 {
    // 我的思路，生成n个丑数之后直接排序，不用管生成的先后顺序
    // 因为n只有1690，所以可以直接用一个set表示该丑数是否存在
    // 错了错了完全错了，用2*n也不能保证会出现排序后第n的答案呀
    public int nthUglyNumber(int n) {
        Set<Integer> set = new HashSet<>();
        int[] ugArray = new int[2*n];                           // 这里2*n是因为大的数可能会先生成
        ugArray[0] = 1;
        int index = 1;
        for (int i = 0; i < 2*n; i++) {
            if (index >= 2*n) break;
            if (!set.contains(ugArray[i]*2)) {
                ugArray[index] = ugArray[i] * 2;
                set.add(ugArray[i] * 2);
                index++;
            }
            if (!set.contains(ugArray[i]*3) && index < 2*n) {
                ugArray[index] = ugArray[i] * 3;
                set.add(ugArray[i] * 3);
                index++;
            }
            if (!set.contains(ugArray[i]*5) && index < 2*n) {
                ugArray[index] = ugArray[i] * 5;
                set.add(ugArray[i] * 5);
                index++;
            }
        }
        Arrays.sort(ugArray);
        return ugArray[n-1];
    }

    /**
     * https://leetcode-cn.com/problems/chou-shu-lcof/comments/244578
     * 非常精妙的三指针做法
     */
    public int others_nthUglyNumber(int n) {
        if (n <= 0)
            return -1;
        int[] dp = new int[n];
        dp[0] = 1;
        int id2 = 0, id3 = 0, id5 = 0;
        for (int i = 1; i < n; i++) {
            dp[i] = Math.min(dp[id2] * 2, Math.min(dp[id3] *3, dp[id5] * 5));
            // 这里不用else if的原因是有可能id2(3) * 2 == id3(2) * 3
            // 这种情况两个指针都要后移
            if (dp[id2] * 2 == dp[i])
                id2 += 1;
            if (dp[id3] * 3 == dp[i])
                id3 += 1;
            if (dp[id5] * 5 == dp[i])
                id5 += 1;
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        int n = 1;
        Offer49 offer49 = new Offer49();
        System.out.println(offer49.nthUglyNumber(n));
    }
}
