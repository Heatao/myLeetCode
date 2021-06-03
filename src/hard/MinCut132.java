package hard;

import java.util.Arrays;

public class MinCut132 {
    public int minCut(String s) {
        int len = s.length();
        // 表示从i到j是不是一个回文串
        boolean[][] valid = new boolean[len][len];

        // 用DP判断子串是不是回文
        char[] charArray = s.toCharArray();
        for (int right = 0; right < len; right++) {
            // 注意：left <= right 取等号表示 1 个字符的时候也需要判断
            for (int left = 0; left <= right; left++) {
                if (charArray[left] == charArray[right] && (right - left <= 2 || valid[left + 1][right - 1])) {
                    valid[left][right] = true;
                }
            }
        }

        int[] dp = new int[len];
        Arrays.fill(dp, Integer.MAX_VALUE);
        // j < i
        for (int i = 0; i < len; i++) {
            // 简单优化，这里的意思是0～i是一个回文，这样0的左边是不需要切的，所以可以设为0
            if (valid[0][i]) {
                dp[i] = 0;
                continue;
            }

            for (int j = 0; j < i; j++) {
                if (valid[j + 1][i])
                    dp[i] = Math.min(dp[i], dp[j] + 1);
            }
        }
        return dp[len-1];
    }

    public static void main(String[] args) {
        String s = "aab";
        MinCut132 minCut132 = new MinCut132();
        System.out.println(minCut132.minCut(s));
    }
}
