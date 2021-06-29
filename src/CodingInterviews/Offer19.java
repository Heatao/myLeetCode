package CodingInterviews;

/**
 * 心路历程：时隔大半年又遇到这道题，唯一的记忆是曾经做过。
 * 第一反应是DP，然后觉得因为.*会有多种情况，用递归可能会不错，后来觉得还是DP可行。
 * dp[i][j] 表示 s 的前 i 个字符与 p 中的前 j 个字符是否能够匹配
 * if p[j] != * -> dp[i][j] = dp[i-1][j-1] (s[i] == p[j])
 * if p[j] != * -> false
 * if p[j] == * -> dp[i][j] = dp[i-1][j] || dp[i][j-2] (s[i] == p[j-1]) 【dp[i][j-2]和下面的原因一样，dp[i-1][j]的意思是可能存在s[i]==s[i-1]==p[j-1]，推导出来的，也就是让p[j-1]多出现一次】
 * if p[j] == * -> dp[i][j] = dp[i][j-2] (s[i] == p[j-1]) 【遇到*但是不匹配，*表示前一位不出现，此时s[i]需要匹配p[j-2]】
 *
 * 动态规划的边界条件为 dp[0][0]=true，即两个空字符串是可以匹配的。
 * 最终的答案即为 dp[m][n]，其中 m 和 n 分别是字符串 s 和 p 的长度。由于大部分语言中，字符串的字符下标是从 0 开始的，因此在实现上面的状态转移方程时，需要注意状态中每一维下标与实际字符下标的对应关系。
 */
public class Offer19 {
    public boolean official_isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        // 这里+1的原因是因为dp[0][0]不表示s[0]和p[0]，而是表示空字符串
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        // i从0开始，因为*可以匹配0=长度为0
        for (int i = 0; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j-1) == '*') {
                    dp[i][j] = dp[i][j-2];
                    if (matches(s, p, i, j - 1)) {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                }
                else {
                    if (matches(s, p, i, j)) dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }

        return dp[m][n];
    }

    private boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }
}
