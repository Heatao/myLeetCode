package CodingInterviews;

public class Offer63 {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int len = prices.length;
        int[][] dp = new int[len][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            if (-dp[i-1][1] < prices[i])
                dp[i][1] = dp[i-1][1];
            else dp[i][1] = -prices[i];
        }

        // 这里不用这样做，只需要返回dp[len-1][0]就行了，因为dp[i][0]更新过程中保证了会把大的值传下去
        int maxProfit = 0;
        for (int i = 1; i < len; i++) {
            maxProfit = Math.max(dp[i][0], maxProfit);
        }
        return maxProfit;
    }
}
