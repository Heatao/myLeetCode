package medium;

import java.math.BigInteger;

/**
 * LeetCode50.Pow(x,n)
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 *
 * 说明:
 * -100.0 < x < 100.0
 * n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。
 */
public class MyPow50 {
    /**
     * n可能是负数
     * 这个题的难点还是在于负数变正数的处理上
     */
    public double mySolution_myPow(double x, int n) {
        if (x == 0 || x == 1.0) return x;
        if (x == -1) {
            if (n % 2 == 0) return -x;
            else return x;
        }
        double ans = 1.0;
        long nn = n;
        if (n < 0) {
            nn = -nn;                             //直接变成-n会有问题，因为表示范围会多-1，最简单的方式就是用BigInt
            x = 1/x;
        }
        for (int i = 0; i < nn; i++) {
            ans = ans*x;
            if (ans == 0) return 0;
        }
        return ans;
    }

    public static void main(String[] args) {
        MyPow50 myPow50 = new MyPow50();
        int n = -2147483648;
        double x = -1.0;
        System.out.println(myPow50.mySolution_myPow(x, n));
    }
}
