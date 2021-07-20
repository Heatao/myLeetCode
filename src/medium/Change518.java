package medium;

public class Change518 {
    public int change(int amount, int[] coins) {
        if(amount == 0) return 1;                                           // 易错点，是1哟
        int len = coins.length;
        int[][] dp = new int[len+1][amount+1];
        for(int i = 0; i <= len; i++)
            dp[i][0] = 1;
        for(int i = 1; i <= len; i++) {
            for(int j = 1; j <= amount; j++) {
                int first = 0;
                if(j - coins[i-1] >= 0) {
                    first = dp[i][j - coins[i-1]];
                    // 下面这句是多余的，因为第一次dp[i][j] = dp[i-1][j] + first;就从i-1转移过来了
                    // first += dp[i-1][j - coins[i-1]];
                }
                dp[i][j] = dp[i-1][j] + first;
            }
        }
        return dp[len][amount];
    }

    public static void main(String[] args) {
        Change518 change518 = new Change518();
        int amount = 5;
        int[] coins = {1,2,5};
        System.out.println(change518.change(amount, coins));
    }
}
