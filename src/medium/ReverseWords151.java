package medium;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 请尝试使用 O(1) 额外空间复杂度的原地解法。
 * 如果不用原地的话，用一个列表去存储就行了，Python尤其方便
 *
 * 后面翻了翻题解，发现Java貌似做不到O(1)的空间复杂度，罢了
 */
public class ReverseWords151 {
    public String reverseWords(String s) {
        s = s.trim();
        s = s + " ";
        Deque<String> deque = new ArrayDeque<String>();

        int preIndex = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ')
                continue;
            if (i-1 >= 0 && s.charAt(i-1) == ' ') {
                preIndex = i + 1;
                continue;
            }
            // 此时i指向的是空字符
            deque.addFirst(s.substring(preIndex, i));
            preIndex = i + 1;
        }
        return String.join(" ", deque);
    }

    public static void main(String[] args) {
        ReverseWords151 reverseWords151 = new ReverseWords151();
        String s = "a good   example";
        System.out.println(reverseWords151.reverseWords(s));
    }
}
