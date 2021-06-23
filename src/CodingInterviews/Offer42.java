package CodingInterviews;

/**
 * 下面的代码可以用滚动数组优化，并且第二个for循环完全可以放到第一个里面去
 */
public class Offer42 {
    public int maxSubArray(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = nums[0];
        for (int i = 1; i < len; i++) {
            dp[i] = Math.max(dp[i-1] + nums[i], nums[i]);
        }

        int max = Integer.MIN_VALUE;
        for (int each : dp)
            max = Math.max(max, each);
        return max;
    }
}
