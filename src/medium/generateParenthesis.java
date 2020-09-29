package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode22.括号生成
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * 示例：
 * 输入：n = 3
 * 输出：[
 *        "((()))",
 *        "(()())",
 *        "(())()",
 *        "()(())",
 *        "()()()"
 *      ]
 */
public class generateParenthesis {
    /**
     * 第一反应是用回溯，但没想清楚怎么去做，而且这个应该是不需要遍历所有组合的
     * 暴力的方式就是列举出所有可能的情况，然后验证是否合理
     *
     * 自己写的回溯结果超时了，我裂开，后面修复啦，不要一直去判断是否存在啊！！！
     *
     * 官方的回溯用的是更简单的规则：如果左括号数量不大于 n，我们可以放一个左括号。如果右括号数量小于左括号的数量，我们可以放一个右括号。
     * 官方题解传递的参数是List，感觉比String好
     * @param n
     * @return
     */
    public static List<String> mySolution_generateParenthesis(int n) {
        StringBuilder str = new StringBuilder();
        str.append("(".repeat(Math.max(0, n)));
        str.append(")".repeat(Math.max(0, n)));
        List<String> nowList = new ArrayList<>();
        nowList.add(str.toString());
        moveLeftParenthesis(nowList, str.toString(), n, 0);
        return nowList;
    }

    /**
     * 用于移动可以移动的最右边左括号
     * 是否可以移动？判断当前括号的最后一个位置
     * 0 0位移动0次
     * 1 1位移动1次
     * 2 2位移动2次
     * n n位移动n次
     * nowTag表示当前移动的是第几个(得来的，不能再移动其右边的部分喔
     */
    public static void moveLeftParenthesis(List<String> nowList, String nowParent, int n, int nowTag) {
        int tag = 0;

        for (int i=nowParent.length()-2; i >= 0; i--) {
            if (nowParent.charAt(i) == '(') {
                tag ++;
                int maybeIndex = (n-tag)*2;
                if (tag >= nowTag && i < maybeIndex && nowParent.charAt(i+1) != '('){
                    //String拼接会产生大量的新对象造成空间资源的浪费，最好还是用StringBuilder
                    String newPa = nowParent.substring(0, i) + nowParent.charAt(i+1) + nowParent.charAt(i) + nowParent.substring(i+2);
//                    if (!nowList.contains(newPa)) nowList.add(newPa);
                    nowList.add(newPa);
                    moveLeftParenthesis(nowList, newPa, n, tag);
                }
            }
        }
    }

    /**
     * 错的不应该啊
     * @param nowParent
     * @param n
     */
    public static void testMovePa(String nowParent, int n){
        int tag=0;
        nowParent = "()(())";
        for (int i=nowParent.length()-2; i >= 0; i--) {
            if (nowParent.charAt(i) == '(' && nowParent.charAt(i+1) != '(') {
                tag ++;
                int maybeIndex = (n-tag)*2;
                if (i < maybeIndex){
                    String newPa = nowParent.substring(0, i) + nowParent.charAt(i+1) + nowParent.charAt(i) + nowParent.substring(i+2);
                    if (newPa.equals(")((())")){
                        System.out.println(nowParent);
                        System.out.println(i);
                        System.out.println(tag);
                        System.out.println(maybeIndex);
                    }
                }
            }
        }
    }

    /**
     * 逐步生成的
     * @param n
     * @return
     */
    public List<String> official_generateParenthesis(int n) {
        List<String> ans = new ArrayList<String>();
        backtrack(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }

    public void backtrack(List<String> ans, StringBuilder cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            ans.add(cur.toString());
            return;
        }
        if (open < max) {
            cur.append('(');
            backtrack(ans, cur, open + 1, close, max);
            cur.deleteCharAt(cur.length() - 1);
        }
        if (close < open) {
            cur.append(')');
            backtrack(ans, cur, open, close + 1, max);
            cur.deleteCharAt(cur.length() - 1);
        }
    }

    public static void main(String[] args) {
        //为什么3，4，5，6都很快，7就没有结果呢？因为如果每次都要判断是否存在的话，耗时太长
        int n=7;
        System.out.println(Arrays.toString(mySolution_generateParenthesis(n).toArray()));
//        testMovePa("", n);
    }
}
