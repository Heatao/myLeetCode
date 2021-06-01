package easy;

/**
 * 这道题要求的是买一次，卖一次
 * 设置两个变量，一次存储当前最大利益，一个存储当前位置之前最小的数字
 */
public class MaxProfit121 {
    public int maxProfit(int[] prices) {
        int prevMin = 10000;
        int profit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] > prevMin)
                profit = Math.max(profit, prices[i] - prevMin);
            else prevMin = prices[i];
        }
        return profit;
    }

    /**
     * 看到评论区大佬的做法写的
     * 但是之前自己也想到了DP的第二位用0或1，为什么不做呢？
     * 第i天只有两种状态，不持有或持有股票，当天不持有股票的状态可能来自昨天卖出或者昨天也不持有，同理，当天持有股票的状态可能来自昨天买入或者昨天也持有中
     */
    public int dp_maxProfit(int[] prices) {
        int len = prices.length;
        if (len <= 1)
            return 0;
        // dp[i][0]，表示第i天没持有股票，dp[i][1]表示第i天持有股票
        int[][] dp = new int[len][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];              // 买入股票，当前收益是负数
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);            //+price[i]表示今天卖出去
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] -prices[i]);
        }
        return dp[len-1][0];
    }
}
