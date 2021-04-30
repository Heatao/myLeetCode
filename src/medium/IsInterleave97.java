package medium;

/*
优秀的题解：https://leetcode-cn.com/problems/interleaving-string/solution/cchao-100de-dong-tai-gui-hua-jie-fa-by-f-ro01/
 */
public class IsInterleave97 {
    /**
     * 方法1：回溯，不过这个回溯不需要显式的"回到之前的状态"，因为index不往前一位就直接回到之前状态了
     */
    private boolean tag = false;
    public boolean isInterleave_back1(String s1, String s2, String s3) {
        if(s1.length() + s2.length() != s3.length())
            return false;
        backtrack3string(s1, s2, s3, 0, 0, 0);
        return tag;
    }
    private void backtrack3string(String s1, String s2, String s3, int indexs1, int indexs2, int indexs3) {
        if (indexs1 == s1.length() && indexs2 == s2.length() && indexs3 == s3.length()){
            tag = true;
            return;
        }
        if (tag) return;

        if (indexs1 < s1.length() && s1.charAt(indexs1) == s3.charAt(indexs3)) {
            backtrack3string(s1, s2, s3, indexs1 + 1, indexs2, indexs3 + 1);
        }

        if (indexs2 < s2.length() && s2.charAt(indexs2) == s3.charAt(indexs3)) {
            backtrack3string(s1, s2, s3, indexs1, indexs2 + 1, indexs3 + 1);
        }
    }

    /**
     * 方法1的修改，写一个类里的全局变量tag太蠢了，可以返回true和false
     */
    public boolean isInterleave_back2(String s1, String s2, String s3) {
        if(s1.length() + s2.length() != s3.length())
            return false;
        return backtrack3string2(s1, s2, s3, 0, 0, 0);
    }
    private boolean backtrack3string2(String s1, String s2, String s3, int indexs1, int indexs2, int indexs3) {
        if (indexs1 == s1.length() && indexs2 == s2.length() && indexs3 == s3.length()){
            return true;
        }

        if (indexs1 < s1.length() && s1.charAt(indexs1) == s3.charAt(indexs3)
                && backtrack3string2(s1, s2, s3, indexs1 + 1, indexs2, indexs3 + 1)) {
            return true;
        }

        /*
        本来这两个if我是这样写的，但是这样会报错喔～要想清除为什么
        if (indexs2 < s2.length() && s2.charAt(indexs2) == s3.charAt(indexs3)) {
            return backtrack3string2(s1, s2, s3, indexs1, indexs2 + 1, indexs3 + 1);
        }
        原因是如果我返回backtrack3string2下一层递归的结果，那么一旦遇到false的情况，就被直接返回了，而没有进入下一个if的"回溯"！
        看上面那个解法的代码，一旦存在true就把tag置为true，这里也是，除非你出现了true的结果，才返回true，不然不返回
         */
        if (indexs2 < s2.length() && s2.charAt(indexs2) == s3.charAt(indexs3)
                && backtrack3string2(s1, s2, s3, indexs1, indexs2 + 1, indexs3 + 1)) {
            return true;
        }
        return false;
    }

    /**
     *
     * 如果写成了上面第二个解法的情况，那么很容易看出可以用备忘录进行优化，避免递归中重复的部分
     * 其实下面代码中为true的结果是可以不用纪录的，因为遇到true会一直返回到回溯的入口（因为是在if判断中回溯的）
     */
    public boolean isInterleave_back3(String s1, String s2, String s3) {
        if(s1.length() + s2.length() != s3.length())
            return false;
        int[][] mem = new int[s1.length()][s2.length()];
        return backtrack3string3(s1, s2, s3, 0, 0, 0, mem);
    }
    private boolean backtrack3string3(String s1, String s2, String s3, int indexs1, int indexs2, int indexs3, int[][] mem) {
        if (indexs1 == s1.length() && indexs2 == s2.length() && indexs3 == s3.length()){
            return true;
        }
        if (mem[indexs1][indexs2] == -1)
            return false;
        else if (mem[indexs1][indexs2] == 1)
            return true;

        if (indexs1 < s1.length() && s1.charAt(indexs1) == s3.charAt(indexs3)
                && backtrack3string2(s1, s2, s3, indexs1 + 1, indexs2, indexs3 + 1)) {
            mem[indexs1 + 1][indexs2] = 1;
            return true;
        }

        if (indexs2 < s2.length() && s2.charAt(indexs2) == s3.charAt(indexs3)
                && backtrack3string2(s1, s2, s3, indexs1, indexs2 + 1, indexs3 + 1)) {
            mem[indexs1][indexs2 + 1] = 1;
            return true;
        }
        mem[indexs1][indexs2] = 1;
        return false;
    }

    /**
     * 有了备忘录的递归算法，那自然而然就有DP啦
     * giao，字符串优先考虑DP喔～
     * dp[i][j] = dp[i][j-1] || dp[i-1][j]
     * dp[i][j]为字符子串s1[0, i),s2[0, j)能否组成s3[0, i+j)
     */
    public boolean isInterleave_dp(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length(), k = s3.length();
        if(m + n != k)
            return false;
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                //这样写的话初始状态为dp[0][0]，而dp[0][0]在这里的定义里是true，不涉及字符串的
                //左闭右开的好处！
                if (j > 0) {
                    if (dp[i][j-1] && s2.charAt(j-1) == s3.charAt(i+j-1))
                        dp[i][j] = true;
                }
                if (i > 0 && !dp[i][j]) {
                    if (dp[i-1][j] && s1.charAt(i-1) == s3.charAt(i+j-1))
                        dp[i][j] = true;
                }
                //最后就不需要再把dp[i][j]设为false了，因为默认为false
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        IsInterleave97 isInterleave97 = new IsInterleave97();
        String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbcbcac";
        String s32 = "aadbbbaccc";

        System.out.println(isInterleave97.isInterleave_dp(s1, s2, s3));
    }
}
