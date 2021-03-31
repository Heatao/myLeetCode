package hard;

/**
 * 编辑距离：
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 * 你可以对一个单词进行如下三种操作：
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 *
 * 虽然一开始没有思路，也想不到DP，但是"最少操作数"，而且没有要求具体方案，这就是暗示DP呀
 */
public class MinDistance72 {
    /*
     * 写到一半发现完全错误了，没有考虑一个单词出现两次的情况，所以DP方差就有问题，i是代表前i个字符而非第i个字符
     * 相等的时候参考的是[i-1][j-1]，这也是为什么题解的矩阵都要初始留一位
     */
    public int minDistance(String word1, String word2) {
        int word1Len = word1.length();
        int word2Len = word2.length();
        if (word1Len == 0 || word2Len == 0) {
            return Math.max(word1Len, word2Len);
        }

        int[][] dp = new int[word1Len][word2Len];

        //初始化两条边界
        if (word1.charAt(0) == word2.charAt(0))
            dp[0][0] = 0;
        else dp[0][0] = 1;
        for (int i = 1; i < word1Len; i++) {
            if (word1.charAt(i) != word2.charAt(0))
                dp[i][0] = dp[i-1][0] + 1;
            else dp[i][0] = dp[i-1][0];
        }

        for (int j = 1; j < word2Len; j++) {
            if (word2.charAt(j) != word1.charAt(0))
                dp[0][j] = dp[0][j-1] + 1;
            else dp[0][j] = dp[0][j-1];
        }

        //循环更新
        for (int i = 1; i < word1Len; i++) {
            for (int j = 1; j < word2Len; j++) {
                //
            }
        }
        return dp[word1Len-1][word2Len-1];
    }

    public int minDistance2(String word1, String word2) {
        int word1Len = word1.length();
        int word2Len = word2.length();
        if (word1Len == 0 || word2Len == 0) {
            return Math.max(word1Len, word2Len);
        }

        int[][] dp = new int[word1Len+1][word2Len+1];

        //初始化两条边界，注意边界是<=
        for (int i = 0; i <= word1Len; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= word2Len; j++) {
            dp[0][j] = j;
        }

        //循环更新
        for (int i = 1; i <= word1Len; i++) {
            for (int j = 1; j <= word2Len; j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = Math.min(Math.min(dp[i-1][j] + 1, dp[i][j-1] + 1), dp[i-1][j-1]);
                }
                else dp[i][j] = Math.min(Math.min(dp[i-1][j] + 1, dp[i][j-1] + 1), dp[i-1][j-1] + 1);
            }
        }
        return dp[word1Len][word2Len];
    }

    public static void main(String[] args) {
        MinDistance72 minDistance72 = new MinDistance72();
        String word1 = "pneumonoultramicroscopicsilicovolcanoconiosis";
        String word2 = "ultramicroscopically";
        System.out.println(minDistance72.minDistance2(word1, word2));
    }

}
