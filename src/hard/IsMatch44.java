package hard;

import java.util.Arrays;

/**
 * LeetCode44.通配符匹配
 * 需要完全匹配
 */
public class IsMatch44 {
    /**
     * 和之前一道题很类似，用DP做，关键是想清楚状态转移方程，状态，初始条件（结束）
     * 用DP的原因是因为遇到*的时候，很难弄清楚*匹配到第几位，所以需要将所有可能都排列一遍
     * 比如abcd*cd*cd可以匹配的太多了，遍历所有组合然后消除荣誉的情况比较适合用DP
     * dp[i][j] 表示s的前i个和p的前j个是否匹配
     */
    public boolean mySolution_isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];               //初始化为+1，因为dp[i][j]表示的是之前i和j是否全部匹配
        //boolean数组初始化就为false，所以不需要单独初始化dp[0][i]的情况
        //初始化边界
        dp[0][0] = true;
        for (int j = 1; j< s.length()+1; j++) {
            if (p.charAt(j-1) == '*')
                dp[0][j] = true;
            else break;
        }

        //想清楚这里两个循环是画表，横竖表，如果将s视为横，p视为竖，那么是竖着遍历的
        //note：画表不是按照匹配顺序来的！而是枚举来的，当前的dp[i][j]只和之前的有关
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                if (s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '?') {
                    dp[i][j] = dp[i-1][j-1];
                }
                else if (p.charAt(j-1) == '*'){
                    //p的第j个字符(下标是[j-1])是*的情况
                    //情况1：使用*只匹配1位，相当于s[i-1]==p[j-1],dp[i][j] = dp[i-1][j-1]
                    //情况2：使用*匹配很多位,如果j-1都被匹配的话，dp[x][j] = true -> dp[i][j] = dp[i-1][j]
                    //      首先理解里层循环是一个列，所以[i-1][j]相对于[i-1][j-1]表示，[j-1]这个'*'匹配了s的第i个，也就是s[i-1]
                    //      后来我反映过来，dp只关注当前，并且这里的双层循环是枚举，所以不需要考虑*匹配多个的情况
                    //上述二者合并，使用了*，就从dp[i-1][j]转移过来
                    //情况3：使用*匹配空，dp[i][j] = dp[i][j-1]
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                }
                else
                    dp[i-1][j-1] = false;
            }
        }
        return dp[s.length()][p.length()];
    }
}
