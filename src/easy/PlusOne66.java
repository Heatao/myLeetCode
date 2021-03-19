package easy;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * 这个题在Python中直接用join然后转int就行了
 * 但是在Java中，除非引入额外包org.apache.commons.lang3，不然不可以直接StringUtils.join
 * 一个个遍历吧
 *
 * 常识：
 * unsigned   int     0～4294967295   (10位数，4e9)
 *
 * int                        -2147483648～2147483647  (10位数，2e9   2^31 - 1)   
 *
 * long long：           -9223372036854775808～9223372036854775807  (19位数， 9e18 ) 2^63 - 1
 *
 * unsigned long long：0～18446744073709551615  (20位数，1e19)  2^64 - 1
 */
public class PlusOne66 {
    /**
     * 这个题不能用parseLong或者parseInt，因为测试用例会非常大
     */
    public int[] plusOne(int[] digits) {
        StringBuilder sb = new StringBuilder();
        for (int each : digits) {
            sb.append(each);
        }
        String tmpStr = sb.toString();
        BigInteger bint = new BigInteger(tmpStr);
        bint = bint.add(new BigInteger("1"));
        tmpStr = String.valueOf(bint.toString());
        String[] tmpList = tmpStr.split("");
        //这里，因为是数组，所以用Arrays.stream,用Stream.of也是一样的，因为stream.of对于数组类型也是用的前者
        return Arrays.stream(tmpList).mapToInt(Integer::parseInt).toArray();
    }

    /**
     * 下面这个就是纯粹没动脑子
     */
    public int[] plusOne_ans2(int[] digits) {
        digits[digits.length-1] += 1;
        return digits;
    }

    /**
     * 根据题意加一，没错就是加一这很重要，因为它是只加一的所以有可能的情况就只有两种：
     *
     * 除 99 之外的数字加一；
     * 数字 99。
     * 加一得十进一位个位数为 00 加法运算如不出现进位就运算结束了且进位只会是一。
     *
     * 所以只需要判断有没有进位并模拟出它的进位方式，如十位数加 11 个位数置为 00，如此循环直到判断没有再进位就退出循环返回结果。
     *
     * 然后还有一些特殊情况就是当出现 9999、999999 之类的数字时，循环到最后也需要进位，出现这种情况时需要手动将它进一位。
     *
     * 链接：https://leetcode-cn.com/problems/plus-one/solution/java-shu-xue-jie-ti-by-yhhzw/
     */
    public int[] others_plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] = digits[i] % 10;
            if (digits[i] != 0) return digits;
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }
}
