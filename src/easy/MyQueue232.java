package easy;

import java.util.Deque;
import java.util.LinkedList;

public class MyQueue232 {
    Deque<Integer> stack;
    Deque<Integer> negStack;

    /** Initialize your data structure here. */
    public MyQueue232() {
        stack = new LinkedList<>();
        negStack = new LinkedList<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        stack.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if(!negStack.isEmpty()) {
            return negStack.pop();
        }
        else {
            movdeStack();
            return negStack.pop();
        }
    }

    /** Get the front element. */
    public int peek() {
        if(!negStack.isEmpty()) {
            return negStack.peek();
        }
        else {
            movdeStack();
            return negStack.peek();
        }
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        if(stack.isEmpty() && negStack.isEmpty())
            return true;
        else return false;
    }

    private void movdeStack() {
        if(!this.stack.isEmpty() && this.negStack.isEmpty()) {
            while(!this.stack.isEmpty()) {
                this.negStack.push(this.stack.pop());
            }
        }
    }
}
