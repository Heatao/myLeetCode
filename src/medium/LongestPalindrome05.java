package medium;

import javax.sound.midi.Soundbank;
import java.util.Stack;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * 示例 1：
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 *
 * babab
 */
public class LongestPalindrome05 {
    public static String mySolution_longestPalindrome(String s) {

        /*
         * 设置一个标志位，记录目前最长的字符串开始位置，和字符串长度
         * 每次右移的指针，当前left，left-1，left+1是否是回文，注意left的位置
         * 左指针指向前一个回文的开始位置
         *
         * 下面这段代码有问题
         */
        int startIndex = 0, maxLen = 0;
        char [] s_list = s.toCharArray();

        int left = 0, right = 0;                                //  left记录前一个回文开始位置
        while (right < s_list.length){
            int temp = left;
            left = right;
            if (isPalin(s_list, temp+1, right)) {
                if (maxLen <= right-temp){
                    maxLen = right-temp;
                    startIndex = temp + 1;
                }
                left = temp + 1;
            }
            if (isPalin(s_list, temp, right)) {
                if (maxLen <= right-temp + 1){
                    maxLen = right-temp + 1;
                    startIndex = temp;
                }
                left = temp;
            }
            if (isPalin(s_list, temp-1, right)) {
                if (maxLen <= right-temp + 2){
                    maxLen = right-temp + 2;
                    startIndex = temp - 1;
                }
                left = temp - 1;
            }
            right ++;
//            System.out.println("left " + left + " start " + startIndex + " " + s.substring(startIndex, startIndex+maxLen));
        }
//        System.out.println("startIndex" + startIndex);
//        System.out.println("maxLen" + maxLen);
        return s.substring(startIndex, startIndex+maxLen);
    }

    public static Boolean isPalin(char [] sList, int start, int end) {
        /**
         * 这里其实没必要用栈
         */
        if (sList.length == 0 || start < 0 || end < 0) return false;
        Stack<Character> st = new Stack<>();
        for (int i = start; i <= end; i++){
            st.push(sList[i]);
        }
        for (int i = start; i <= end; i++){
            Character topChar = st.pop();
            if (topChar != sList[i]) return false;
        }
        return true;
    }

    public boolean validPalindromic(char [] charArray, int left, int right){
        while(left < right){
            if (charArray[left] != charArray[right]) {
                return false;
            }
            left ++;
            right ++;
        }
        return true;
    }

    public static String official_longestPalindrome(String s) {
        /**
         * 官方的一个解法是利用动态规划
         * 题目中说可以假设最大长度为1000，也就是暗示可以用o(n^2)的算法
         *
         * 状态：dp[i][j] 表示子串s[i...j]是否是回文子串
         * 状态转移方程：dp[i][j] = (s[i] == s[j]) and dp[i+1][j-1]
         * 因为是下标访问，所以一定需要考虑下标是否有效
         * 边界：j-1-(i+1)+1 < 2，整理得 j - i < 3；这个边界的意思是，子串不构成区间，子串长度<=1时，dp[i+1][j-1]没有意义
         * 意思是s[i...j]长度为2或者3时，不用检查子串是否回文，不需要状态转移
         *
         * 初始化：dp[i][j] = true
         * 输出：在得到一个状态的值为true的时候，记录起始位置和长度，填表完成以后再截取
         *
         * 动态规划实际上是填表
         * 由于dp[i][j]参考了它左下角的值，所以填表的时候需要，先升序填列，再升序填行
         */

        int startIndex = 0, maxLen = 1;         //这里maxLen为1，因为当各个字符不一样时，貌似不会更新到
        char [] s_list = s.toCharArray();
        int len = s_list.length;

        //特殊情况，那个什么corner
        if (len < 2) return s;

        Boolean[][] dp = new Boolean[len][len];

        //初始化
        for (int i=0; i < len; i++){
            dp[i][i] = true;
        }

        for (int j=0; j < len; j++){
            for (int i=0; i < j; i++){
                if (s_list[i] != s_list[j]){
                    dp[i][j] = false;
                }
                else {
                    if (j - i < 3){
                        dp[i][j] = true;
                    }
                    else dp[i][j] = dp[i+1][j-1];
                }

                if (dp[i][j] && j-i+1 >= maxLen){
                    maxLen = j-i+1;
                    startIndex = i;
                }
            }
        }
        return s.substring(startIndex, startIndex+maxLen);
    }

    public static void main(String[] args) {
        String s = "babadada";
        System.out.println(s);
        System.out.println(do2nd_DP(s));
        System.out.println(official_longestPalindrome(s));
    }

    /**
     * 下面的做法超时了
     */
    private String do2nd(String s) {
        // 最暴力的做法：判断所有的子串是否是回文，这样做会超时
        int max = 0;
        String maxStr = "";
        for(int i = 0; i < s.length(); i++) {
            for(int j = i+1; j <= s.length(); j++) {               // 第二个for应该+1，因为substr是左闭右开
                String substr = s.substring(i, j);
                int thisLen = substr.length();
                if(thisLen == 1 || judge(substr).length() == thisLen) {
                    if(thisLen > max) {
                        max = thisLen;
                        maxStr = substr;
                    }
                }
            }
        }
        return maxStr;
    }

    private String judge(String substr) {
        StringBuilder sb = new StringBuilder(substr);
        String strr = sb.reverse().toString();
        if(substr.equals(strr)) return substr;
        return "";
    }

    private static String do2nd_DP(String s) {
        // 我太傻了，光顾着想状态应该从上一个字符传来，然而却不知道定义一个dp[i][j]来表示边界
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        int max = 0;
        String maxStr = "";
        // 初始化
        for(int i = 0; i < len; i++)
            dp[i][i] = true;
        // 开始dp
        // l 写为2可以减少2次循环
        for(int l = 2; l < len; l++) {                      // 这里i不能设置为子出岸的左边界，因为这样的话，长的字符串中的短字符串都还没有遍历到！！！
            for(int i = 0; i < len; i++) {                    // 这里i的循环判断不能写为l，因为l表示长度，长度和首位置有什么关系吗
                int j = i + l - 1;
                if (j >= len)
                    break;
                if(s.charAt(i) == s.charAt(j)) {
                    // 两个相邻的数字相等也是true的，这里初始化的时候没有初始化到
                    if (j - i <= 2) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                    if(l > max && dp[i][j]) {                        // 这里也需要判断它到底是不是回文！只凭首尾两个元素是不行滴
                        max = l;
                        maxStr = s.substring(i, j+1);
                    }
                }
            }
        }
        return maxStr;
    }

    public String do2nd_DP_wrong(String s) {
        // 我太傻了，光顾着想状态应该从上一个字符传来，然而却不知道定义一个dp[i][j]来表示边界
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        int max = 0;
        String maxStr = "";
        // 初始化
        for(int i = 0; i < len; i++)
            dp[i][i] = true;
        // 开始dp
        for(int i = 0; i < len; i++) {
            for(int j = i+1; j < len; j++) {
                if(s.charAt(i) == s.charAt(j) && dp[i+1][j-1]) {
                    dp[i][j] = true;
                    if(j-i+1 > max) {
                        max = j-i+1;
                        maxStr = s.substring(i, j+1);
                    }
                }
            }
        }
        return maxStr;
    }
}
