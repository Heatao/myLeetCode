package hard;

import java.util.Arrays;

/**
 * 一眼看过去没有思路，但猜测是DP
 */
public class NumDistinct115 {
    public int numDistinct(String s, String t) {
        if (t.length() > s.length())
            return 0;
        int sLen = s.length();
        int tLen = t.length();
        //注意这里的行和列是什么哟
        int[][] dp = new int[tLen+1][sLen+1];
        // 初始化边界，因为一开始new的时候就是0，所以只需要填充第一行为1
        Arrays.fill(dp[0], 1);

        //先按行遍历，所以是t的长度
        for (int i = 1; i <= t.length(); i++) {
            for (int j = 1; j <= s.length(); j++) {
                if (s.charAt(j-1) == t.charAt(i-1))
                    dp[i][j] = dp[i-1][j-1] + dp[i][j-1];
                else
                    dp[i][j] = dp[i][j-1];
            }
        }
        return dp[tLen][sLen];
    }
}
