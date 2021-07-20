package medium;

import java.util.Arrays;

public class CanPartition416 {
    // dp[i][j]表示前i个，和为j的情况是否成立
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int each: nums)
            sum += each;
        if(sum % 2 != 0) return false;
        sum /= 2;
        int len = nums.length;
        if (len < 2) return false;

        boolean[][] dp = new boolean[len+1][sum+1];
        // 下面这个初始化有问题，要注意dp数组的定义
        // Arrays.fill(dp[0], true);
        for (int i = 0; i <= len; i++) {
            dp[i][0] = true;
        }
        for(int i = 1; i <= len ;i++) {
            for(int j = 0; j <= sum; j++) {
                if(j - nums[i-1] >= 0)
                    dp[i][j] = dp[i-1][j] | dp[i-1][j - nums[i-1]];
                else dp[i][j] = dp[i-1][j];
            }
        }
        return dp[len][sum];
    }

    public static void main(String[] args) {
        int[] nums = {1,2,5};
        CanPartition416 canPartition416 = new CanPartition416();
        System.out.println(canPartition416.canPartition(nums));
    }
}
