package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * 我觉得应该用递归
 * 用备忘录算法就可以通过来，不然会超时，还可以改为DP
 */
public class WordBreak139 {
    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> wordSet = new HashSet<>(wordDict);
        int[] backArrays = new int[s.length()];
        backTrack(s, 0, wordSet, backArrays);
        return tag;
    }

    boolean tag = false;
    private void backTrack(String s, int index, HashSet<String> wordSet, int[] backArrays) {
        if (tag || index >= s.length()) {
            tag = true;
            return;
        }
        if (backArrays[index] == -1)
            return;

        //左闭右开！！！别忘了
        for (int j = index+1; j <= s.length(); j++) {
            if (wordSet.contains(s.substring(index, j))) {
                backTrack(s, j, wordSet, backArrays);
            }
        }
        backArrays[index] = -1;
    }

    public boolean wordBreakDP(String s, List<String> wordDict) {
        int len = s.length();
        if (len == 0 || wordDict.size() == 0)
            throw new RuntimeException("Incorrect input data.");
        HashSet<String> wordSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[len+1];
        dp[0] = true;
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordSet.contains(s.substring(j, i)))
                    dp[i] = true;
            }
        }
        return dp[len];
    }

    public static void main(String[] args) {
        WordBreak139 wordBreak139 = new WordBreak139();
        String s = "catsandog";
        String[] list = {"cats","dog","sand","and","cat"};
        List<String> wordDict = Arrays.asList(list);
        System.out.println(wordBreak139.wordBreakDP(s, wordDict));
    }
}
