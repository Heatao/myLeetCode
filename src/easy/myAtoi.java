package easy;

import java.util.HashMap;
import java.util.Map;

/**
 *LeetCode8.字符串转换整数
 * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。接下来的转化规则如下：
 * 如果第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字字符组合起来，形成一个有符号整数。
 * 假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成一个整数。
 * 该字符串在有效的整数部分之后也可能会存在多余的字符，那么这些字符可以被忽略，它们对函数不应该造成影响。
 * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换，即无法进行有效转换。
 *
 * 在任何情况下，若函数不能进行有效的转换时，请返回 0
 */
public class myAtoi {
    public static int mySolution_myAtoi(String str) {
        //先trim空格
        str = str.trim();
        if (str.equals("")) return 0;

        StringBuilder stringBuffer = new StringBuilder();
        int tag = 1;
        char thisChar = str.charAt(0);
        if (thisChar == '-'){
            tag = -1;
        }
        else if (thisChar == '+') {
        }
        else if (Character.isDigit(thisChar))
            stringBuffer.append(thisChar);
        else return 0;

        //再用正则函数判断第一个字符
        int len = str.length();
        for (int i = 1; i < len; i++) {
            thisChar = str.charAt(i);
            if (Character.isDigit(thisChar)){
                stringBuffer.append(thisChar);
            }
            else break;
        }
        if (stringBuffer.toString().equals(""))
            return 0;

        //记录已经写入的长度，判断是否超过
        int x_int;
        try {
            x_int = Integer.parseInt(stringBuffer.toString());
        }catch (NumberFormatException e){
            if (tag==1) return Integer.MAX_VALUE;
            else return Integer.MIN_VALUE;
        }
        x_int = tag*x_int;
        return x_int;
    }

    /**
     * 官方翻译版：用形式语言与自动机的概念
     */
    class Automaton {
        final String START = "start";
        final String SIGNED = "signed";
        final String IN_NUM = "in_number";
        final String END = "end";
        String state = START;
        Map<String, String[]> map;
        public int sign = 1;
        public long ans = 0;

        public Automaton() {
            map = new HashMap<>();
            map.put(START, new String[]{START, SIGNED, IN_NUM, END});
            map.put(SIGNED, new String[]{END, END, IN_NUM, END});
            map.put(IN_NUM, new String[]{END, END, IN_NUM, END});
            map.put(END, new String[]{END, END, END, END});
        }

        public int get_col(char c) {
            if (c == ' ') return 0;
            if (c == '+' || c == '-') return 1;
            if (c >= '0' && c <= '9') return 2;
            return 3;
        }

        public void get(char c) {
            state = map.get(state)[get_col(c)];
            if (state.equals(IN_NUM)) {
                ans = ans * 10 + c - '0';
                if (sign == 1) {
                    ans = Math.min(ans, Integer.MAX_VALUE);
                } else {
                    // -(long)Integer.MIN_VALUE，这个操作有点东西，不然越界
                    ans = Math.min(ans, -(long)Integer.MIN_VALUE);
                }
            } else if (state.equals(SIGNED))
                sign = c == '+' ? 1 : -1;
        }
    }

    public int myAtoi(String str) {
        Automaton automaton = new Automaton();
        char[] c = str.toCharArray();
        for (char ch : c) {
            automaton.get(ch);
        }
        return automaton.sign * ((int) automaton.ans);
    }

    public static void main(String[] args) {
        String str = "20000000000000000000";
        String str2 = "    -42";
        String str3 = ".1";
        String str1 = String.valueOf(Integer.MIN_VALUE);
        System.out.println(mySolution_myAtoi(str));
        System.out.println(do2nd(str));
        System.out.println(do2nd(str1));
        System.out.println(do2nd(str2));
        System.out.println(do2nd(str3));
    }

    private static int do2nd(String s) {
        /*
         * 先用trim删除空格，然后判断第一位是不是+-数字，
         * 循环判断每一位是不是0-9的数字，之前的值*10+当前数字，
         * 如果值大于int最大值则判断其符号，符号为负数则判断当前数字是不是1，是的话返回最小值
         */
        s = s.trim();
        char[] sl = s.toCharArray();
        int symble = 1;
        int thisNum = 0;                                            // 易错点：一开始应该为0
        if(sl[0] == '-')
            symble = -1;
        else if(ifNum(sl[0])) thisNum = sl[0] - '0';
        else return 0;                                              // 易错点
        for(int i = 1; i < s.length(); i++) {
            if(!ifNum(sl[i])) return thisNum;
            else {
                if(thisNum * 10 < thisNum) {
                    if(symble == 1)
                        return Integer.MAX_VALUE;
                    else return Integer.MIN_VALUE;
                }
                thisNum = thisNum*10 + sl[i] - '0';
            }
        }
        return symble*thisNum;                                     // 易错点：别忘了符号
    }

    private static boolean ifNum(char c) {
        if(c - '0' >= 0 && c - '0' <= 9) return true;
        else return false;
    }
}
