package hard;

import java.util.*;

/**
 * LeetCode32.最长有效括号
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 * 示例 1:
 * 输入: "(()"
 * 输出: 2
 * 解释: 最长有效括号子串为 "()"
 */
public class LongestValidParentheses32 {
    /**
     * 看到这种需要对称的结构，比如左右括号，第一反应是用栈
     * 这里需要注意的是"最长"，比如"()(()"最长为2
     * ()(()(, )()()), ()((), (()(((()
     * 于是意识到其实很适合用动态规划
     *
     * 用栈的话需要去分析什么时候子串会断裂：先遇到)的时候；结束时候栈不为空。
     * 这两种情况下，子串都只为valid长度，不需要加上之前长度
     *
     * 下面这段代码是错的，因为我认为只有当栈为空是才会把前一个子串冻住，但是(()(((，前面(()的时候，第一个()没有冻住
     */
    public int mySolution_longestValidParentheses(String s) {
        int validLen = 0;
        int frozenLen = 0;
        int maxLen = 0;
        Stack<Character> parenStack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '('){
                if (parenStack.isEmpty()){
                    //栈为空表示一个新状态，把当前的validLen加到frozenlen里面
                    frozenLen += validLen;
                    maxLen = Math.max(maxLen, frozenLen);
                    validLen = 0;
                }
                parenStack.push('(');
            }
            else{
                //else一定是)
                if (!parenStack.isEmpty()){
                    //如果当前栈不为空，则与前一个凑对啦
                    parenStack.pop();
                    validLen += 2;
                    if (parenStack.isEmpty()){
                        //栈为空表示一个新状态，把当前的validLen加到frozenlen里面
                        frozenLen += validLen;
                        maxLen = Math.max(maxLen, frozenLen);
                        validLen = 0;
                    }
                }
                else {
                    //子串断裂,frozen置0,此时当前子串为validLen长度
                    frozenLen = 0;
                    maxLen = Math.max(maxLen, validLen);
                    validLen = 0;
                }
            }
        }
        //第二种情况，此时子串为valid长度
        maxLen = Math.max(maxLen, validLen);
        return maxLen;
    }

    public int stack_longestValidParentheses(String s) {
        int maxLen = 0;
        ArrayList<Integer> sList = new ArrayList<>();
        Deque<Integer> parenStack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '('){
                parenStack.push(i);
            }
            else {
                if (!parenStack.isEmpty()){
                    sList.add(parenStack.pop());
                    sList.add(i);
                }
            }
        }
        Collections.sort(sList);
        if (sList.isEmpty())
            return 0;
        //这里tmp必须从1开始，因为每次加1的话，第一个是不算的
        int tmp = 1;
        for (int i = 1; i < sList.size(); i++) {
            if (sList.get(i) - sList.get(i-1) == 1){
                tmp += 1;
            }
            else{
                maxLen = Math.max(maxLen, tmp);
                tmp = 1;
            }
        }
        return Math.max(maxLen, tmp);
    }

    /**
     * 用栈的改良
     * 之前的思路：当扫描到右括号，它匹配最近一个左括号，即栈顶元素被匹配而出栈，有效长度 = 当前索引-出栈的索引+1，所以上面的代码需要tmp=1
     * 如果在弹栈的时候操作，需要一个"参照物"，记录当前长度，也记录之前的子串在哪断裂
     * https://leetcode-cn.com/problems/longest-valid-parentheses/solution/shou-hua-tu-jie-zhan-de-xiang-xi-si-lu-by-hyj8/
     */
    public int others_stack_longestValidParentheses(String s) {
        if (s == null || s.length() == 0) return 0;
        Deque<Integer> stack = new ArrayDeque<>();
        //在栈中预置索引 -1 作为一个“参照物”，并改变计算方式：当前索引 - 出栈后新的栈顶索引。
        stack.push(-1);
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(')
                stack.push(i);
            else {
                stack.pop();
                //它后面可能也出现一段有效长度，它要成为 -1 那样的“参照物”。它之前出现的有效长度已经计算过了，栈中的 -1 的使命已经完成了，要被替代。
                if (stack.isEmpty())
                    stack.push(i);
                else {
                    res = Math.max(res, i - stack.peek());
                }
            }
        }
        return res;
    }

    /**
     * 我们用 dp[i] 表示以 i 结尾的最长有效括号；
     * 当 s[i] 为 (,dp[i] 必然等于 0，因为不可能组成有效的括号；
     * 那么 s[i] 为 )
     * 2.1 当 s[i-1] 为 (，那么 dp[i] = dp[i-2] + 2；
     * 2.2 当 s[i-1] 为 ) 并且 s[i-dp[i-1] - 1] 为 (，那么 dp[i] = dp[i-1] + 2 + dp[i-dp[i-1]-2]；
     * 最后这里2.2的地方还需要加上dp[i-dp[i-1]-2]，是因为可能出现**(，**的地方也可能已经是配对的字符串
     *
     * 时间复杂度：O(n)O(n)。
     *
     * 拿到这种题目后，不要慌，根据题目中是否有：计数、最大/最小/最长、是否存在等字眼，先判断是否可以使用动态规划解决，如果可以，然后根据上面的步骤，一步一步进行分析，尤其是最后一步这一步分析，是能否转化为子问题的关键。
     * 链接：https://leetcode-cn.com/problems/longest-valid-parentheses/solution/dong-tai-gui-hua-si-lu-xiang-jie-c-by-zhanganan042/
     */
    public int others_dp_longestValidParentheses(String s) {
        if (s == null || s.length() == 0) return 0;
        int[] dp = new int[s.length()];
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i > 0 && s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i - 2 >= 0 ? dp[i - 2] + 2 : 2);
                } else if (s.charAt(i - 1) == ')' && i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + 2 + (i - dp[i - 1] - 2 >= 0 ? dp[i - dp[i - 1] - 2] : 0);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
