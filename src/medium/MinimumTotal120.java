package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 如果是需要返回路径的话，我会想到回溯，如果是返回最小值，我会想到DP
 * 额外要求是只使用 O(n) 的额外空间（n 为三角形的总行数）来解决，就是滚动数组优化的意思
 */
public class MinimumTotal120 {
    public int minimumTotal(List<List<Integer>> triangle) {
        int m = triangle.size();
        int n = triangle.get(m-1).size();
        int[][] dp = new int[m][n];

        dp[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < i + 1; j++) {
                if (j != 0 && j != i)
                    dp[i][j] = Math.min(dp[i-1][j], dp[i-1][j-1]) + triangle.get(i).get(j);
                else if (j == i) {
                    dp[i][j] = dp[i-1][j-1] + triangle.get(i).get(j);
                }
                else dp[i][j] = dp[i-1][j] + triangle.get(i).get(j);
            }
        }

        int min = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            if (min > dp[m-1][j])
                min = dp[m-1][j];
        }
        return min;
    }

    /**
     * 用两个数组优化还是可以试一试的，还可以进一步优化为一个数组，但是我觉得自己容易错
     */
    public int official_minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] f = new int[2][n];
        f[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < n; ++i) {
            int curr = i % 2;
            int prev = 1 - curr;
            f[curr][0] = f[prev][0] + triangle.get(i).get(0);
            for (int j = 1; j < i; ++j) {
                f[curr][j] = Math.min(f[prev][j - 1], f[prev][j]) + triangle.get(i).get(j);
            }
            f[curr][i] = f[prev][i - 1] + triangle.get(i).get(i);
        }
        int minTotal = f[(n - 1) % 2][0];
        for (int i = 1; i < n; ++i) {
            minTotal = Math.min(minTotal, f[(n - 1) % 2][i]);
        }
        return minTotal;
    }

    public static void main(String[] args) {
        MinimumTotal120 minimumTotal120 = new MinimumTotal120();
        Integer[][] tri = {{2}, {3,4}, {6,5,7}, {4,1,8,3}};
        List<List<Integer>> triangle = new ArrayList<>();
        for (Integer[] t : tri) {
            triangle.add(Arrays.asList(t));
        }

        System.out.println(minimumTotal120.minimumTotal(triangle));
    }
}
