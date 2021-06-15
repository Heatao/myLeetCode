package hard;

/**
 * 其他买卖股票的题：121，122，123
 * 股票买卖看storm大佬这篇就行了：https://leetcode-cn.com/circle/article/qiAgHn/
 * DP数组定义：
 *  T[i][k][0] 表示在第 i 天结束时，最多进行 k 次交易且在进行操作后持有 0 份股票的情况下可以获得的最大收益；
 *  T[i][k][1] 表示在第 i 天结束时，最多进行 k 次交易且在进行操作后持有 1 份股票的情况下可以获得的最大收益
 *
 * 基准情况：
 *  T[-1][k][0] = 0,
 *  T[-1][k][1] = -Infinity,
 *  T[i][0][0] = 0,
 *  T[i][0][1] = -Infinity
 *
 * 状态转义方程：
 *  T[i][k][0] = max(T[i - 1][k][0], T[i - 1][k][1] + prices[i])
 *  T[i][k][1] = max(T[i - 1][k][1], T[i - 1][k - 1][0] - prices[i])
 */
public class MaxProfit188 {
    public int maxProfit(int k, int[] prices) {
        if (prices == null || k == 0 || prices.length == 0)
            return 0;

        int n = prices.length;
        int[][][] dp = new int[n][k+1][2];

        // 初始化
        /*
        * 为什么下面两个不用初始化呢，因为k=0的时候就直接不用计算啦
        * dp[i][0][0] = 0;
        * dp[i][0][1] = -prices[0];
        * */
        for (int i = 0; i <= k; i++) {
            dp[0][i][0] = 0;
            dp[0][i][1] = -prices[0];
        }

        // 开始状态转移
        for (int i = 1; i < n; i++) {
            for (int j = k; j > 0; j--) {
                dp[i][j][0] = Math.max(dp[i-1][j][0], dp[i-1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i-1][j][1], dp[i-1][j-1][0] - prices[i]);
            }
        }

        return dp[n-1][k][0];
    }
}
