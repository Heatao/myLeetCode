package CodingInterviews;

import java.util.*;

public class Offer60 {
    /**
     * 最朴素的做法：用回溯求出所有可能的值，然后用哈希表统计这些值出现的次数，求出概率
     * 下面的做法是对的，但是在n为11的时候会超时，很难受
     */
    public double[] dicesProbability(int n) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        backtrack(n, hashMap, 0,0);
        List<Integer> keyList = new ArrayList<>(hashMap.keySet());
        Collections.sort(keyList);
        // 这里的sum不用这样求，因为结果的总数是固定的，通过pow(6,n)即可求到
        double sum = 0;
        for (Integer eachCmt : hashMap.values()) {
            sum += eachCmt;
        }
        double[] ans = new double[keyList.size()];
        int key;
        for (int i = 0; i < ans.length; i++) {
            key = keyList.get(i);
            ans[i] = hashMap.get(key) / sum;
        }
        return ans;
    }

    private void backtrack(int n, HashMap<Integer, Integer> hashMap, int tmpSum, int index) {
        if (index >= n) {
            if (hashMap.containsKey(tmpSum)) hashMap.put(tmpSum, hashMap.get(tmpSum) + 1);
            else hashMap.put(tmpSum, 1);
            return;
        }
        for (int i = 1; i <= 6; i++) {
            tmpSum += i;
            backtrack(n, hashMap, tmpSum, index+1);
            tmpSum -= i;
        }
    }

    /**
     * 其他同学的做法，用DP来做
     * https://leetcode-cn.com/problems/nge-tou-zi-de-dian-shu-lcof/comments/246119
     * n 个骰子「点数和」的范围为 [n, 6n] ，数量为 6n - n + 1 = 5n + 1 种
     * 总数也可以通过pow(6,n)得到
     * 初始状态为第一个骰子扔完，即1-6
     */
    public double[] others_dicesProbability(int n) {
        int[][] dp = new int[n+1][n*6+1];                   // 这里设计n+1的为了让第一位正好表示第几个骰子，毕竟没有第0枚骰子的说法
        double[] doubles = new double[5*n+1];
        for (int i = 1; i <= 6; i++) {
            dp[1][i] = 1;
        }
        double all = Math.pow(6, n);

        for (int i = 1; i <= n; i++) {                      // 这里需要从1开始，因为把double的赋值放到了for循环内
            for (int j = i; j <= 6*n; j++) {                // 第二层循环判断为6*n是因为值的范围最大为6*n
                for (int k = 1; k <= 6; k++) {
                    // 有可能总值更小呢，所以会<0
                    if (j-k < 0) break;
                    dp[i][j] += dp[i-1][j-k];
                    if (i == n) {
                        // 更新放到这里可以使时间再降低，这里j-i是使下标从0开始
                        doubles[j-i] = dp[i][j] / all;
                    }
                }
            }
        }
        return doubles;
    }

    public static void main(String[] args) {
        Offer60 offer60 = new Offer60();
        int n = 1;
        System.out.println(Arrays.toString(offer60.others_dicesProbability(n)));
    }
}
