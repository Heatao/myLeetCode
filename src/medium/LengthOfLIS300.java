package medium;

/**
 * 希望要求要么O(n^2)，要么O(n logn)
 * 得到提示用DP之后，自己想出了O(n^2)的方案
 */
public class LengthOfLIS300 {
    // 注意返回值是长度喔，而不是计算最大值
    public int lengthOfLIS(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = 1;
        int max = 1;                                                // 易错点
        for (int i = 1; i < len; i++) {
            dp[i] = 1;                                              // 易错点
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        LengthOfLIS300 lengthOfLIS300 = new LengthOfLIS300();
        int[] nums = {10,9,2,5,3,7,101,18};
        int[] nums1 = {7,7,7,7,7,7};
        System.out.println(lengthOfLIS300.lengthOfLIS(nums));
        System.out.println(lengthOfLIS300.lengthOfLIS(nums1));
    }

    /**
     * https://leetcode-cn.com/problems/longest-increasing-subsequence/solution/zui-chang-shang-sheng-zi-xu-lie-dong-tai-gui-hua-2/
     * 用二分查找做，但是这样的话是不能得到序列的
     */
    private int others(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int[] tails = new int[nums.length];
        int res = 0;
        int left = 0, right = 0;
        for(int num : nums) {
            left = 0;                                       // 易错点
            right = res;                                    // 易错点
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (tails[mid] < num)
                    left = mid + 1;
                else right = mid;
            }
            tails[left] = num;                              // 易错点
            if (res == right)                               // 易错点
                res += 1;
        }
        return res;
    }
}
