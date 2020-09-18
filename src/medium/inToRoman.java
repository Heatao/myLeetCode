package medium;

/**
 * LeetCode12.整数转罗马数组
 * 贪心
 */
public class inToRoman {
    /**
     * 从高位到低位，取余.遇上4或者9，则向排列上一位
     * @param num
     * @return
     */
    public static String mySolution_intToRoman(int num) {
        if (num < 1 || num > 3999) return null;

        StringBuilder aimRoman = new StringBuilder();

        int M = num / 1000;
        num = num % 1000;
        aimRoman.append("M".repeat(M));

        int D = num / 500;
        num = num % 500;
        aimRoman.append("D".repeat(D));

        int C = num / 100;
        num = num % 100;
        //原来难点在这里，900不好判断啊，毕竟前面有个500，但也只可能存在一个500
        for (int i = 0; i < C; i++) {
            if (aimRoman.toString().contains("D") && C==4){
                aimRoman.deleteCharAt(aimRoman.length()-1);
                aimRoman.append("CM");
                break;
            }
            else if (C==4) {
                aimRoman.append("CD");
                break;
            }
            aimRoman.append("C");
        }

        int L = num / 50;
        num = num % 50;
        aimRoman.append("L".repeat(L));

        int X = num /10;
        num= num % 10;
        for (int i = 0; i < X; i++) {
            if (aimRoman.toString().contains("L") && X==4){
                aimRoman.deleteCharAt(aimRoman.length()-1);
                aimRoman.append("XC");
                break;
            }
            else if (X==4) {
                aimRoman.append("XL");
                break;
            }
            aimRoman.append("X");
        }

        int V = num / 5;
        num = num % 5;
        aimRoman.append("V".repeat(V));

        int I = num;
        for (int i = 0; i < I; i++) {
            if (aimRoman.toString().contains("V") && I==4){
                aimRoman.deleteCharAt(aimRoman.length()-1);
                aimRoman.append("IX");
                break;
            }
            else if (I==4) {
                aimRoman.append("IV");
                break;
            }
            aimRoman.append("I");
        }

        return aimRoman.toString();
    }

    /**
     * 作者：liweiwei1419
     * 链接：https://leetcode-cn.com/problems/integer-to-roman/solution/tan-xin-suan-fa-by-liweiwei1419/
     * @param num
     * @return
     */
    public String other_inToroman(int num){
        // 把阿拉伯数字与罗马数字可能出现的所有情况和对应关系，放在两个数组中
        // 并且按照阿拉伯数字的大小降序排列，这是贪心选择思想
        int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder stringBuilder = new StringBuilder();
        int index = 0;
        while (index < 13) {
            // 特别注意：这里是等号
            while (num >= nums[index]) {
                // 注意：这里是等于号，表示尽量使用大的"面值"
                stringBuilder.append(romans[index]);
                num -= nums[index];
            }
            index++;
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        int num = 9;
        System.out.println(mySolution_intToRoman(num));
    }
}
