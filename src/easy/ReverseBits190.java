package easy;

/**
 * 位运算基本知识：
 *
 * 与&：0&0=0 0&1=0 1&0=0 1&1=1
 * 或|：0|0=0 0|1=1 1|0=1 1|1=1
 * 异或^：0^0=0 0^1=1 1^0=1 1^1=0
 * 取反~：~1=0 ~0=1
 * 左移<<：左边的二进制位丢弃，右边补0
 * 右移>>：正数左补0，负数左补1，右边丢弃
 * 无符号左移<<<：左边的二进制位丢弃，右边补0
 * 无符号右移>>>：忽略符号位，空位都以0补齐
 */
public class ReverseBits190 {
    // you need treat n as an unsigned value
    /*
    最朴素的想法，直接reverse，显然是错的，因为需要的是反转二进制
     */
    public int reverseBits(int n) {
        StringBuilder sb = new StringBuilder();
        // 这里看到第一步就错了
        System.out.println(Integer.toBinaryString(n));
        sb.append(Integer.toBinaryString(n));
        sb = sb.reverse();
        return Integer.parseInt(sb.toString(), 2);
    }

    public int foolish_reverseBits(int n) {
        System.out.println(Integer.reverseBytes(n));
        return Integer.reverse(n);
    }

    public int official_reverseBits(int n) {
        int rev = 0;
        for (int i = 0; i < 32 && n != 0; ++i) {
            rev |= (n & 1) << (31 - i);     // n&1的结果和n的最后一位相同，等于是取出了n的最后一位，再把这一位左移31-i位就移到了翻转之后对称的位置
            n >>>= 1;                       // 再将n右移一位
        }
        return rev;
    }

    public static void main(String[] args) {
        int n = 43261596;
        ReverseBits190 reverseBits190 = new ReverseBits190();
        System.out.println(reverseBits190.foolish_reverseBits(n));
    }
}
