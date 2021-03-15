package medium;

import java.util.Arrays;

/**
 * 为什么不用回溯？因为不需要返回原来的路径呀，只会向下或者向右
 * 所以直接单纯递归即可，但是会超时，因为会有重复，想一想哪个斐波那契数列
 * 记忆化递归其实就是DP的上一步，备忘录算法
 */
public class UniquePaths62 {
    public int uniquePaths(int m, int n) {
        int[][] memSet = new int[m+1][n+1];
        return memBack(m, n, memSet);
    }

    private int memBack(int m, int n, int[][] memSet) {
        if (m <= 0 || n <= 0)
            return 0;
        //此时只有一条路可走，故为1
        if (m == 1 || n == 1) {
            return 1;
        }
        if (memSet[m][n] <= 0) {
            memSet[m][n] = memBack(m-1, n, memSet) + memBack(m, n-1, memSet);
        }
        return memSet[m][n];
    }

    public int uniquePathsDP(int m, int n) {
        int[][] dp = new int[m][n];

        //初始边界
        Arrays.fill(dp[0], 1);
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }

        //当然是从1开始，因为0是初始边界，不是重复子结构
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }
}
