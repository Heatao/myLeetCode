package CodingInterviews;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 还记得第一次做的时候是O(n)的时间插入和删除，根本不符合栈的要求
 */
public class MinStack30 {
    Deque<Integer> stack;
    Deque<Integer> minStack;
    // 不能用mini
    // int mini;

    /** initialize your data structure here. */
    public MinStack30() {
        stack = new LinkedList<>();
        minStack = new LinkedList<>();
        // 要用miniStack前一位进行判断
        minStack.push(Integer.MAX_VALUE);
    }

    public void push(int x) {
        stack.push(x);
        minStack.push(Math.min(minStack.peek(), x));
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return minStack.peek();
    }
}
