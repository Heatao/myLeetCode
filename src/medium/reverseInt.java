package medium;

/**
 * LeetCode7.数字反转
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 *
 * 示例 1:
 * 输入: 123
 * 输出: 321
 *
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2^31,  2^31 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 *
 * 知识点：整数溢出，int y=-x时，如果x在二进制反转时溢出，则y不会是x的相反数
 */
public class reverseInt {
    public static int mySolution_reverse(int x) {
        int tag = 1;
        long y;
        if (x < 0) {
            y = (long)(x);
            y = -y;
            tag = -1;
        }
        else y = (long)x;
        System.out.println(y);

        String x_str = String.valueOf(y);
        int len = x_str.length();
        StringBuffer sBuff = new StringBuffer();

        for (int i=len-1; i>=0; i--){
            sBuff.append(x_str.charAt(i));
        }
        long x_int = Long.parseLong(sBuff.toString());
        if (x_int > Integer.MAX_VALUE || x_int < Integer.MIN_VALUE)
            return 0;
        int xInt = (int)x_int;
        if (tag == -1) xInt = -xInt;
        return xInt;
    }

    public static int official_reverse(int x) {
        /**
         * 我觉得官方的代码有多余，因为int类型最大数和最小数开头的数字只能是1或2，
         * 所以如果有最后一次循环的话，pop的值一定为1或2，所以(rev == INT_MAX / 10 && pop > 7)和(rev == INT_MIN / 10 && pop < -8)判断可以省略。
         */
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE/10) return 0;
            if (rev < Integer.MIN_VALUE/10) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }

    public static void main(String[] args) {
        int test = -2147483648;
        System.out.println(official_reverse(test));
        System.out.println(Integer.MAX_VALUE+" "+Integer.MIN_VALUE);
    }
}
