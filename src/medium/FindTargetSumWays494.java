package medium;

import java.util.Arrays;

public class FindTargetSumWays494 {
    public int findTargetSumWays(int[] nums, int target) {
        // dp[i][j]表示前i个数字运算得到j的次数
        if(nums == null) return 0;

        // target为负和正是一样的
        if(target < 0) target = -target;

        int len = nums.length;
        int[][] dp = new int[len+1][target+1];                  // 这样定义是错误的，因为会忽略中间过程为负的情况
        dp[0][0] = 1;

        for(int i = 1; i <= len; i++) {
            for(int j = 0; j <= target; j++) {
                int first = 0, second = 0;
                if(j+nums[i-1] <= target) first = dp[i-1][j+nums[i-1]];
                if(j-nums[i-1] >= 0) second = dp[i-1][j-nums[i-1]];
                dp[i][j] = first + second;
            }
        }
        return dp[len][target];
    }

    public static void main(String[] args) {
        FindTargetSumWays494 findTargetSumWays494 = new FindTargetSumWays494();
        int[] nums = {1,1,1,1,1};
        int target = 3;
        System.out.println(findTargetSumWays494.findTargetSumWays_others(nums, target));
    }

    private int findTargetSumWays_others(int[] nums, int s) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++)
            sum += nums[i];
        // 绝对值范围超过了sum的绝对值范围则无法得到
        if (Math.abs(s) > Math.abs(sum)) return 0;
        int len = nums.length;
        int range = sum * 2 + 1;//因为要包含负数所以要两倍，又要加上0这个中间的那个情况
        int[][] dp = new int[len][range];//这个数组是从总和为-sum开始的
        //加上sum纯粹是因为下标界限问题，赋第二维的值的时候都要加上sum
        // 初始化   第一个数只能分别组成+-nums[i]的一种情况
        dp[0][sum + nums[0]] += 1;
        dp[0][sum - nums[0]] += 1;
        for (int i = 1; i < len; i++) {
            for (int j = -sum; j <= sum; j++) {
                if((j+nums[i]) > sum) {//+不成立 加上当前数大于了sum   只能减去当前的数
                    dp[i][j+sum] = dp[i - 1][j - nums[i] + sum];
                }else if((j-nums[i]) < -sum) {//-不成立  减去当前数小于-sum   只能加上当前的数
                    dp[i][j+sum] = dp[i - 1][j + nums[i] + sum];
                }else {//+-都可以
                    dp[i][j+sum] = dp[i-1][j+nums[i]+sum]+dp[i-1][j-nums[i]+sum];
                }
            }
        }
        return dp[len - 1][sum + s];
    }
}
