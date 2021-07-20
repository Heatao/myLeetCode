package medium;

import java.util.Arrays;

/**
 * 完全背包和01背包的区别就是每个东东可以无限个
 */
public class CoinChange322 {
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;
        int len = coins.length;
        int[][] dp = new int[len+1][amount+1];
        Arrays.fill(dp[0], amount+1);                     // 本来想写最大值，但是这样会溢出，这里是易错点
        dp[0][0] = 0;
        for(int i = 1; i <= len; i++) {
            for(int j = 0; j <= amount; j++) {
                dp[i][j] = dp[i-1][j];
                if(j-coins[i-1]>=0) dp[i][j] = Math.min(dp[i][j], dp[i][j-coins[i-1]] + 1);
                if(j-coins[i-1]>=0) dp[i][j] = Math.min(dp[i][j], dp[i-1][j-coins[i-1]] + 1);
            }
        }
        return dp[len][amount] > amount ? -1 : dp[len][amount];                 // 这里也是易错点
    }

    public static void main(String[] args) {
        int[] coins = {2};
        int target = 3;
        CoinChange322 coinChange322 = new CoinChange322();
        System.out.println(coinChange322.coinChange(coins, target));
    }
}
