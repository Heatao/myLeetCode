package CodingInterviews;

import java.util.HashSet;
import java.util.Set;

/**
 * 刚开始刷lc做过的一道题，现在完全忘记了orz
 * 看了题解发现只要用哈希表而不是哈希set来做就可以避免内层的for循环呢
 * https://leetcode-cn.com/problems/zui-chang-bu-han-zhong-fu-zi-fu-de-zi-zi-fu-chuan-lcof/solution/mian-shi-ti-48-zui-chang-bu-han-zhong-fu-zi-fu-d-9/
 */
public class Offer48 {
    // 提交第三次终于改对了，写完了意识到这道题其实应该用滑动窗口
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        int len = s.length();
        Set<Character> set = new HashSet<>();
        int[] dp = new int[len];
        dp[0] = 1;
        set.add(s.charAt(0));
        // 第一个for循环计算长度
        for (int i = 1; i < len; i++) {
            char thisChar = s.charAt(i);
            if (!set.contains(thisChar)) {
                set.add(thisChar);
                dp[i] = dp[i-1] + 1;
            }
            // 这里不能直接置0
            else {
                set.clear();
                set.add(thisChar);
                dp[i] = 1;
                int index = i-1;
                while (index >= 0 && thisChar != s.charAt(index)) {
                    set.add(s.charAt(index));
                    dp[i]++;
                    index--;
                }
            }
        }
        // 第二个for循环计算最大值
        int max = 0;
        for (int each : dp)
            max = Math.max(max, each);
        return max;
    }

    public static void main(String[] args) {
        Offer48 offer48 = new Offer48();
        String s1 = "dvdf";
        String s2 = "bbbbb";
        String s3 = "abcabcbb";
        String s4 = "pwwkew";
        System.out.println(offer48.lengthOfLongestSubstring(s4));
    }
}
