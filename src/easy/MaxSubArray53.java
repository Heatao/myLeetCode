package easy;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 下意识就觉得应该用DP，但是要弄清楚这个下意识是什么？我觉得是需要遍历每一种情况，这时的"每种情况"需要改变题目要求
 */
public class MaxSubArray53 {
    /**
     * 优化：空间复杂度从O(n)降低到O(1)，只用常数个变量来存，只需要保存最大值和前一位就行
     */
    public int maxSubArray(int[] nums) {
        int[] maxs = new int[nums.length];
        maxs[0] = nums[0];
        for (int i=1; i < nums.length; i++) {
            maxs[i] = Math.max(maxs[i-1] + nums[i], nums[i]);
        }
        int ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (ans < maxs[i])
                ans = maxs[i];
        }
        return ans;
    }

    /**
     * 更高级的做法
     * 用分治法，先将区间缩小到最小再扩大
     */
    public class Status {
        public int lSum, rSum, mSum, iSum;

        public Status(int lSum, int rSum, int mSum, int iSum) {
            this.lSum = lSum;
            this.rSum = rSum;
            this.mSum = mSum;
            this.iSum = iSum;
        }
    }

    public int others_maxSubArray(int[] nums) {
        return getInfo(nums, 0, nums.length - 1).mSum;
    }

    public Status getInfo(int[] a, int l, int r) {
        if (l == r) {
            return new Status(a[l], a[l], a[l], a[l]);
        }
        int m = (l + r) >> 1;
        Status lSub = getInfo(a, l, m);
        Status rSub = getInfo(a, m + 1, r);
        return pushUp(lSub, rSub);
    }

    public Status pushUp(Status l, Status r) {
        int iSum = l.iSum + r.iSum;
        int lSum = Math.max(l.lSum, l.iSum + r.lSum);
        int rSum = Math.max(r.rSum, r.iSum + l.rSum);
        int mSum = Math.max(Math.max(l.mSum, r.mSum), l.rSum + r.lSum);
        return new Status(lSum, rSum, mSum, iSum);
    }

    /**
     * 再一次做，连DP的暗示都没读出来，甚至不如第一次
     * 看评论有人说也可以用greedy，有的要求返回数组的，用greedy貌似比较何时
     */
    private int do2nd(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = nums[0];
        int max = dp[0];
        for(int i = 1; i < len; i++) {
            dp[i] = Math.max(dp[i-1] + nums[i], nums[i]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
