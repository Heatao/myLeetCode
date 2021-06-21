package CodingInterviews;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 我的思路：
 * 1. 一定poped出现了pushed向前的顺序，那么poped的之后顺序就不能出现该元素之后的值
 * 2. 模拟栈进出
 */
public class Offer31 {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed == null || popped == null || pushed.length != popped.length)
            return false;
        int len = pushed.length;
        Deque<Integer> stack = new LinkedList<>();
        int pIndex = 0;
        for (int i = 0; i < len; i++) {
            stack.push(pushed[i]);
            while (!stack.isEmpty() && stack.peek() == popped[pIndex]) {
                stack.pop();
                pIndex++;
            }
        }
        while (!stack.isEmpty() && pIndex < len) {
            if (stack.peek() == popped[pIndex]) {
                pIndex++;
                stack.pop();
            }
            else return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Offer31 offer31 = new Offer31();
        int[] pushed1 = {1,2,3,4,5};
        int[] poped1 = {4,5,3,2,1};
        int[] poped2 = {4,3,5,1,2};
        System.out.println(offer31.validateStackSequences(pushed1, poped1));
        System.out.println(offer31.validateStackSequences(pushed1, poped2));
    }
}
