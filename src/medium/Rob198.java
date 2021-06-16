package medium;

public class Rob198 {
    /**
     * 一上来感觉是DP，而且和股票问题很像，想用二维数组的DP做，写不出状态转移方程
     * 瞄了一眼题解才发现应该用一维数组
     * 下面的解答是错误的，因为还有这种情况[2,1,1,2]
     */
    public int rob(int[] nums) {
        int len = nums.length;
        if (len == 1)
            return nums[0];
        int[] dp = new int[len];
        dp[0] = nums[0];
        // 这里有边界的一个坑，dp[1]必须是两间房最大的那个
        // 因为dp[i]的含义就是表示i的最大值
        dp[1] = Math.max(nums[1], nums[0]);
        for (int i = 2; i < len; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]);
        }

        return Math.max(dp[len-1], dp[len-2]);
    }
}
