package medium;

/**
 * LeetCode29.两数相除
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 * 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
 */
public class TwoNumDivide29 {
    /**
     * 这个题目的难点在于结果溢出的判断，应该要结合位数的运算
     * 下面自己写的这段代码是超时的，实际解法要么用递归，要么用位运算
     */
    public static int mySolution_divide(int dividend, int divisor) {
        int symbolTag = 1;
        if ((dividend<0 && divisor>0) || (dividend>0 && divisor<0)){
            symbolTag = -1;
        }

        //单独处理溢出的情况
        int dividendOverflow = 0;
        if (dividend == -2147483648){
            if (divisor == -1) return 2147483647;
            if (divisor == -2147483648) return 1;
            dividend += 1;
            dividendOverflow = 1;
        }
        else if (divisor == -2147483648){
            return 0;
        }

        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);
        int output = 0;
        while(dividend>=divisor){
            output += 1;
            dividend -= divisor;
            if (dividendOverflow == 1){
                dividend += 1;
                dividendOverflow = 0;
            }
        }
        if (symbolTag == -1){
            output = -output;
        }
        return output;
    }

    /**
     * 来自https://github.com/foxleezh/leetcode-java
     * 其实这里还用了long，如果用负数则可以避免这个问题，但是负数俺实在不想去想了
     *
     * 解题思路：这题是除法，所以先普及下除法术语
     * 商，公式是：(被除数-余数)÷除数=商，记作：被除数÷除数=商...余数，是一种数学术语。
     * 在一个除法算式里，被除数、余数、除数和商的关系为：(被除数-余数)÷除数=商，记作：被除数÷除数=商...余数，
     * 进而推导得出：商×除数+余数=被除数。
     * 要求商，我们首先想到的是减法，能被减多少次，那么商就为多少，但是明显减法的效率太低
     * 那么我们可以用位移法，因为计算机在做位移时效率特别高，向左移1相当于乘以2，向右位移1相当于除以2
     * 我们可以把一个dividend（被除数）先除以2^n，n最初为31，不断减小n去试探,当某个n满足dividend/2^n>=divisor时，
     * 表示我们找到了一个足够大的数，这个数*divisor是不大于dividend的，所以我们就可以减去2^n个divisor，以此类推
     *
     * 我们可以以100/3为例
     * 2^n是1，2，4，8...2^31这种数，当n为31时，这个数特别大，100/2^n是一个很小的数，肯定是小于3的，所以循环下来，
     * 当n=5时，100/32=3, 刚好是大于等于3的，这时我们将100-32*3=4，也就是减去了32个3，接下来我们再处理4，同样手法可以再减去一个3
     * 所以一共是减去了33个3，所以商就是33
     * 这其中得处理一些特殊的数，比如divisor是不能为0的，Integer.MIN_VALUE和Integer.MAX_VALUE
     *
     */
    public int others_divide(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        boolean negative;
        negative = (dividend ^ divisor) <0;//用异或来计算是否符号相异
        long t = Math.abs((long) dividend);
        long d= Math.abs((long) divisor);
        int result = 0;
        for (int i=31; i>=0;i--) {
            if ((t>>i)>=d) {//找出足够大的数2^n*divisor
                result+=1<<i;//将结果加上2^n
                t-=d<<i;//将被除数减去2^n*divisor
            }
        }
        return negative ? -result : result;//符号相异取反
    }

    public static void main(String[] args) {
        int a = -2147483648;
        int b = 2;
        System.out.println(mySolution_divide(a, b));
    }
}
