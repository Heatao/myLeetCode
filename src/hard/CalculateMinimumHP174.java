package hard;

import java.util.Arrays;

/**
 * 不要求给出路径，那么感觉就是用DP的样子
 * 注意:
 * 1.骑士决定每次只向右或向下移动一步
 * 2.如果他的健康点数在某一时刻降至 0 或以下，他会立即死亡
 *
 * 这也就是说其实比较的是路径中的最小值的最大值
 *
 * 下面的题目就是经典的倒序DP
 * 因为对于每一条路径，我们需要同时记录两个值。第一个是「从出发点到当前点的路径和」（当前血量），第二个是「从出发点到当前点所需的最小初始值」（路径所需要最大血量）。
 * 而这两个值的重要程度相同，同时影响后续的决策。也就是说，这样的动态规划是不满足「无后效性」的。（简单来讲，两个参数如果冲突的时候，就不知道如何判断了）
 * 如果从右下到左上，令 dp[i][j] 表示从坐标 (i,j) 到终点所需的最小初始值，就无需担心路径和的问题（不用管当前血量了），只需要关注最小初始值。
 */
public class CalculateMinimumHP174 {
    /**
     * 下面的做法是错误的，这是因为DP需要的是无后向性，例如{{1,-3,3}, {0,-2,0}, {-3,-3,-3}}这个例子
     * 由于加血的过程，需要以来后面的值判断需要最少的血
     * 正着DP的话，当下的最优解并非之后的最优解
     */
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        int[][] dp = new int[m][n];
        int[][] dpMin = new int[m][n];

        dp[0][0] = dungeon[0][0];
        dpMin[0][0] = dungeon[0][0];
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j-1] + dungeon[0][j];
            dpMin[0][j] = Math.min(dp[0][j], dpMin[0][j-1]);
        }
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i-1][0] + dungeon[i][0];
            dpMin[i][0] = Math.min(dp[i][0], dpMin[i-1][0]);
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 如果按照下面这样直接写的话，会导致这样的路径更倾向于被选中（先有很多怪，然后有个比较大的血包）
                // dp[i][j] = dungeon[i][j] + Math.max(dp[i-1][j], dp[i][j-1]);
                if (dpMin[i-1][j] > dpMin[i][j-1]) {
                    dp[i][j] = dungeon[i][j] + dp[i-1][j];
                }
                else dp[i][j] = dungeon[i][j] + dp[i][j-1];

                int prevMin = Math.max(dpMin[i-1][j], dpMin[i][j-1]);
                dpMin[i][j] = Math.min(prevMin, dp[i][j]);
            }
        }

        if (dpMin[m-1][n-1] > 0)
            return 1;
        else return  1-dpMin[m-1][n-1];
    }

    public int second_calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        // 这里设置+1是为了最后公主的位置的时候也可以用相同的形式去计算
        int[][] dp = new int[m+1][n+1];
        for (int i = 0; i <= m; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        dp[m][n - 1] = dp[m - 1][n] = 1;
        for (int i = m-1; i >= 0; i--) {
            for (int j = n-1; j >= 0; j--) {
                int mini = Math.min(dp[i][j+1], dp[i+1][j]);
                dp[i][j] = Math.max(mini - dungeon[i][j], 1);
            }
        }

        return dp[0][0];
    }

    public static void main(String[] args) {
        CalculateMinimumHP174 calculateMinimumHP174 = new CalculateMinimumHP174();
        int[][] dungeon1 = {{-2,-3,3}, {-5,-10,1}, {10,30,-5}};
        int[][] dungeon2 = {{1,-3,3}, {0,-2,0}, {-3,-3,-3}};
        System.out.println(calculateMinimumHP174.calculateMinimumHP(dungeon2));
    }
}
