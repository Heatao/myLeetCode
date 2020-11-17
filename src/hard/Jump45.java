package hard;

/**
 * LeetCode45.跳跃游戏
 */
public class Jump45 {
    /**
     * 直觉用DP
     * dp[i]中存的是到达第i位的最小步数
     * nums[i]表示在i位置可以跳跃的最大长度
     *
     * 如果不优化，会在最后两个测试用例超时
     * 优化的话考虑从内层遍历入手，因为每次都从0开始遍历到i感觉不太合适
     *
     * 优化1：因为dp[x]是递增数组，所以每次找到最近的能跳到i的j就可以break内层循环了，不需要用min去比较
     * 解决了倒数第二个超时
     *
     * 优化2：内层循环的j是递增的，所以不需要每次都从0开始哒！
     *
     * 优化参考：https://leetcode-cn.com/problems/jump-game-ii/solution/dong-tai-gui-hua-tan-xin-yi-dong-by-optimjie/
     */
    public int mySolution_jump(int[] nums) {
        int m = nums.length;
        int[] dp = new int[m];
        dp[0] = 0;
        for (int i = 1, j=0; i < m; i++) {
            //遍历之前的每一个找到最小值
            //优化1，不使用min
            //int minStep = m;

            //优化2，不重制j
            for (; j < i; j++) {
                if (nums[j] + j >= i){
                    //minStep = Math.min(minStep, dp[j] + 1);
                    dp[i] = dp[j]+1;
                    break;
                }
            }
            //dp[i] = minStep;
        }
        return dp[m-1];
    }

    /**
     * 贪心的做法
     * 这道题可以帮助更好的理解贪心算法，题目要求最少的跳跃次数，那么怎么样能保证最少呢？
     * 仔细理解这句话：那就是要保证只有到你不得不跳的时候就去跳一次，
     * 只要当前位置还在你能触及的范围之内，你就选择不跳！
     * 因此我们只要记录2个变量，一个是当前次数下能触及的最大范围max1，
     * 一个是当前次数下再跳一次能触及的最大范围max2，
     * 当位置超过max1时则将当前步数加1，同时max1应该变成什么呢？
     * 显然，因为max2是max1再跳一步的最大范围，现在步数加1了，那么max1变成max2就行了。
     *
     * 来源https://leetcode-cn.com/problems/jump-game-ii/comments/670237
     */
    public int jump(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        // 当前使用的跳跃次数
        int steps = 0;
        // 当前使用的跳跃次数情况下最远能达到的距离
        int curStepsMaxReach = 0;
        // 当前使用的跳跃次数再加一次最远能达到的距离
        int oneMoreStepMaxReach = nums[0];
        for (int i = 1;i < nums.length;i++) {
            // 如果目前位置超过了curStepsMaxReach，则意味着我必须要跳一步了，即step++
            // 同时由于step++了，curStepsMaxReach就变成了oneMoreStepMaxReach
            if (i > curStepsMaxReach) {
                steps++;
                curStepsMaxReach = oneMoreStepMaxReach;
            }
            oneMoreStepMaxReach = Math.max(oneMoreStepMaxReach, i + nums[i]);
        }
        return steps;
    }
}
