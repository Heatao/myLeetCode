package easy;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode13. 罗马数字转整数
 */
public class romanToInt {
    /**
     * 第一：细节没处理好，i+=2;一直写的是i++
     * 第二：有更简单的做法，思维受限
     * @param s
     * @return
     */
    public static int mySolution_romanToInt(String s) {
        int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int s_int = 0;
        char[] s_list = s.toCharArray();
        int roman_index = 0;

        int i=0;
        while (i < s_list.length && roman_index <= 12) {
            while (s_list[i] != romans[roman_index].charAt(0)){
                roman_index += 1;
                if (roman_index > 12) return s_int;
            }

            //如果romans当前为两位，比如CM
            if (romans[roman_index].length() == 2 && i+1 < s_list.length && s_list[i+1] == romans[roman_index].charAt(1)){
                s_int += nums[roman_index];
                i+=2;
                roman_index += 1;
            }
            else if (romans[roman_index].length() == 2){
                roman_index += 1;
            }
            else {
                s_int += nums[roman_index];
                i++;
            }
        }
        return s_int;
    }

    /**
     * 其他人的思路有两种，第一种是用hashmap搭配substring，第二种是利用规则"小的在左边就减"
     * 做法1：https://leetcode-cn.com/problems/roman-to-integer/solution/yong-shi-9993nei-cun-9873jian-dan-jie-fa-by-donesp/
     * 作者：guanpengchn
     * 链接：https://leetcode-cn.com/problems/roman-to-integer/solution/hua-jie-suan-fa-13-luo-ma-shu-zi-zhuan-zheng-shu-b/
     * @param s
     * @return
     */
    public static int others_romanToInt(String s) {
        Map<String, Integer> map = new HashMap<>();
        map.put("I", 1);
        map.put("IV", 4);
        map.put("V", 5);
        map.put("IX", 9);
        map.put("X", 10);
        map.put("XL", 40);
        map.put("L", 50);
        map.put("XC", 90);
        map.put("C", 100);
        map.put("CD", 400);
        map.put("D", 500);
        map.put("CM", 900);
        map.put("M", 1000);

        int ans = 0;
        for(int i = 0;i < s.length();) {
            if(i + 1 < s.length() && map.containsKey(s.substring(i, i+2))) {
                ans += map.get(s.substring(i, i+2));
                i += 2;
            } else {
                ans += map.get(s.substring(i, i+1));
                i ++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(mySolution_romanToInt("MCMXCIV"));
    }
}
