package test;

import java.util.Scanner;


public class Backpack {
    /**
     * 现有一个容量大小为V的背包和N件物品，每件物品有两个属性，体积和价值，请问这个背包最多能装价值为多少的物品？
     *
     * 输入描述:
     * 第一行两个整数V和n。
     * 接下来n行，每行两个整数体积和价值。1≤N≤1000,1≤V≤20000。
     * 每件物品的体积和价值范围在[1,500]。
     *
     * 输出描述:
     * 输出背包最多能装的物品价值。
     */
    public int backPack01(int[] values, int[] weights, int V, int n){
        int maxValue = 0;
        int[][] dp = new int[n+1][V+1];
        for (int i = 1; i <= n; i++) {
            for(int j = 0; j <= V;j++) {
                if (j - weights[i-1] >= 0)
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-weights[i-1]] + values[i-1]);
                else dp[i][j] = dp[i-1][j];
                maxValue = Math.max(maxValue, dp[i][j]);
            }
        }
        return maxValue;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int V = in.nextInt();
        int n = in.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            weights[i] = in.nextInt();
            values[i] = in.nextInt();
        }

        Backpack backpack = new Backpack();
        int maxV = backpack.backPack01(values, weights, V, n);
        System.out.println(maxV);
    }
}
