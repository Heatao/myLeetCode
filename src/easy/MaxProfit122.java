package easy;

/**
 * 这道题要求尽可能的交易,但是不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 */
public class MaxProfit122 {
    public int maxProfit(int[] prices) {
        //用短视的做法，Greedy的方式是不要看未来
        int profit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (i+1 < prices.length && prices[i+1] > prices[i]) {
                profit += prices[i+1] - prices[i];
            }
        }
        return profit;
    }
}
