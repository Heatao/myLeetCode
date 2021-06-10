package medium;

import java.util.HashMap;
import java.util.Map;

public class FractionToDecimal166 {
    public String fractionToDecimal(int numerator, int denominator) {
        float ansNum =  (float) numerator / (float) denominator;
        String ans = String.valueOf(ansNum);
        if (ans.length() < 10000)
            return ans;
        // 本来的想法是判断其除了之后是否到达float的最长长度来进行计算，但是这样果然不行啊
        return null;
    }

    /*
    细节很多：用异或确定正负数，用哈希表存余数，哈希表的值记录位数
     */
    public String official_fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        StringBuilder fraction = new StringBuilder();
        // If either one is negative (not both)
        if (numerator < 0 ^ denominator < 0) {
            fraction.append("-");
        }
        // Convert to Long or else abs(-2147483648) overflows
        long dividend = Math.abs(Long.valueOf(numerator));
        long divisor = Math.abs(Long.valueOf(denominator));
        fraction.append(String.valueOf(dividend / divisor));
        long remainder = dividend % divisor;
        if (remainder == 0) {
            return fraction.toString();
        }
        fraction.append(".");
        Map<Long, Integer> map = new HashMap<>();
        while (remainder != 0) {
            if (map.containsKey(remainder)) {
                fraction.insert(map.get(remainder), "(");
                fraction.append(")");
                break;
            }
            map.put(remainder, fraction.length());
            remainder *= 10;
            fraction.append(String.valueOf(remainder / divisor));
            remainder %= divisor;
        }
        return fraction.toString();
    }

    public static void main(String[] args) {
        FractionToDecimal166 fractionToDecimal166 = new FractionToDecimal166();
        int num1 = 4;
        int num2 = 333;
        System.out.println(fractionToDecimal166.fractionToDecimal(num1, num2));
    }
}
