package medium;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

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
        System.out.println(reverseWords151.do2nd(s));
    }

    private String do2nd(String s) {
        // 注意这道题Java不能O(1)，面试千万不能split
        // 可以用栈去存每一个单词
        // 也可以搞成数组，再用双指针去翻转，先翻转整个数组，再翻转每一个单词
        s = s.trim();
        s = s + ' ';                // 这里只是方便后面循环的时候判断
        Deque<String> stack = new LinkedList<>();
        int left = 0;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == ' ' && i-1 >= 0 && s.charAt(i-1) != ' ') {                // 易错点：判断i-1不是空格
                stack.push(s.substring(left, i));
                left = i;
            }
            else if(s.charAt(left) == ' ') left = i;                                    // 易错点：判断s.charAt(left)而不是left
        }

        return String.join(" ", stack);
    }
}
