package medium;

/**
 * 动态规划分阶段？
 * 并不是，就是DP！！
 *
 * 官方题解写的很好：
 *     (i,j) 位置只能从 (i - 1, j)(i−1,j) 和 (i, j - 1)(i,j−1) 走到，这样的条件就是在告诉我们这里转移是 「无后效性」
 *
 *     动态规划的题目分为两大类，一种是求最优解类，典型问题是背包问题，
 *     另一种就是计数类，比如这里的统计方案数的问题，它们都存在一定的递推性质。
 *     前者的递推性质还有一个名字，叫做 「最优子结构」 ——即当前问题的最优解取决于子问题的最优解，
 *     后者类似，当前问题的方案数取决于子问题的方案数。所以在遇到求方案数的问题时，我们可以往动态规划的方向考虑。
 *
 *     由于这里 f(i, j)f(i,j) 只与 f(i - 1, j)f(i−1,j) 和 f(i, j - 1)f(i,j−1) 相关，我们可以运用「滚动数组思想」把空间复杂度优化称 O(m)
 *     当我们定义的状态在动态规划的转移方程中只和某几个状态相关的时候，就可以考虑这种优化方法，目的是给空间复杂度「降维」
 *
 * 何时用DP，何时用回溯
 * 1.首先看取值范围，递归回溯一维数组，100就是深度的极限了（何况本题是100²）
 * 2.如果是求走迷宫的【路径】，必然是回溯；如果是走迷宫的【路径的条数】，必然是dp
 */
public class UniquePathsWithObstacles63 {
    /**
     * 下面的解法是彻头彻尾的错误，因为没有审题，障碍物可能不止一个
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        //障碍物可能不存在
        int obsm = -1;
        int obsn = -1;
        lable:
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    obsm = i;
                    obsn = j;
                    break lable;
                }
            }
        }
        UniquePaths62 uniquePaths62 = new UniquePaths62();
        int obsPaths = 0;
        if (obsm != -1) {
            int firstPaths = uniquePaths62.uniquePathsDP(obsm+1, obsn+1);
            int secondPaths = uniquePaths62.uniquePathsDP(m-obsm, n-obsn);
            obsPaths = firstPaths * secondPaths;
        }
        int originPaths = uniquePaths62.uniquePathsDP(m, n);
        return originPaths - obsPaths;
    }

    public static void main(String[] args) {
        int[][] obstacle = {{0,0,0}, {0,1,0}, {0,0,0}};
        UniquePathsWithObstacles63 uniquePathsWithObstacles63 = new UniquePathsWithObstacles63();
        System.out.println(uniquePathsWithObstacles63.uniquePathsWithObstacles(obstacle));
    }

    /**
     * 为什么可以用滚动数组，因为这里 f(i, j)f(i,j) 只与 f(i - 1, j)f(i−1,j) 和 f(i, j - 1)f(i,j−1) 相关
     * 所以可以按行更新，f(i,j)自然继承了f(i,j-1)，所以下面的条件是f(j)继承f(j-1)
     */
    public int official_uniquePathsWithObstacles(int[][] obstacleGrid) {
        int n = obstacleGrid.length, m = obstacleGrid[0].length;
        int[] f = new int[m];

        f[0] = obstacleGrid[0][0] == 0 ? 1 : 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (obstacleGrid[i][j] == 1) {
                    f[j] = 0;
                    continue;
                }
                if (j - 1 >= 0 && obstacleGrid[i][j - 1] == 0) {
                    f[j] += f[j - 1];
                }
            }
        }

        return f[m - 1];
    }

    /**
     * https://leetcode-cn.com/problems/unique-paths-ii/solution/jian-dan-dpbi-xu-miao-dong-by-sweetiee/
     */
    public int others_uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0) {
            return 0;
        }

        // 定义 dp 数组并初始化第 1 行和第 1 列。
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m && obstacleGrid[i][0] == 0; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n && obstacleGrid[0][j] == 0; j++) {
            dp[0][j] = 1;
        }

        // 根据状态转移方程 dp[i][j] = dp[i - 1][j] + dp[i][j - 1] 进行递推。
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}
