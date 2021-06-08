package medium;

public class MaxProduct152 {
    public int maxProduct(int[] nums) {
        int len = nums.length;
        if (len == 1)
            return nums[0];

        // 0表示正，1表示负
        // 注意下面一定要写2，不然会爆内存
        int[][] dp = new int[len][2];
        if (nums[0] > 0)
            dp[0][0] = nums[0];
        else dp[0][1] = nums[0];

        int max = nums[0];
        for (int i = 1; i < len; i++) {
            if (nums[i] > 0) {
                dp[i][0] = Math.max(dp[i-1][0] * nums[i], nums[i]);
                dp[i][1] = Math.min(dp[i-1][1] * nums[i], nums[i]);
            }
            else {
                dp[i][0] = Math.max(dp[i-1][1] * nums[i], nums[i]);
                dp[i][1] = Math.min(dp[i-1][0] * nums[i], nums[i]);
            }
            max = Math.max(max, dp[i][0]);
        }

        return max;
    }

    public static void main(String[] args) {
        MaxProduct152 maxProduct152 = new MaxProduct152();
        int[] nums1 = {2,3,-2,4};
        int[] nums2 = {-2,0,-1};
        System.out.println(maxProduct152.maxProduct(nums2));
    }
}
