package CodingInterviews;

import java.util.Deque;
import java.util.LinkedList;

public class CQueue09 {
    private Deque<Integer> stack1;
    private Deque<Integer> stack2;
    private boolean orderTag;

    public CQueue09() {
        stack1 = new LinkedList<>();
        stack2 = new LinkedList<>();
        orderTag = true;
    }

    public void appendTail(int value) {
        if (this.orderTag)
            stack1.push(value);
        else {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
            Deque<Integer> tmp = stack1;
            stack1 = stack2;
            stack2 = tmp;
            stack2.clear();
            this.orderTag = true;
        }
    }

    public int deleteHead() {
        if (stack1.isEmpty())
            return -1;
        int ans;
        if (!this.orderTag)
            ans = stack1.pop();
        else {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
            ans = stack2.pop();
            // 如果按照下面这样写的话，stack1和stack2都会指向同一个，然后stack2情况，二者都会清空
            // stack1 = stack2;
            // stack2.clear();
            Deque<Integer> tmp = stack1;
            stack1 = stack2;
            stack2 = tmp;
            stack2.clear();
            this.orderTag = false;
        }
        return ans;
    }

    /*
    下面是Krahets的做法，很精妙!
    LinkedList<Integer> A, B;
    public CQueue() {
        A = new LinkedList<Integer>();
        B = new LinkedList<Integer>();
    }
    public void appendTail(int value) {
        A.addLast(value);
    }
    public int deleteHead() {
        if(!B.isEmpty()) return B.removeLast();
        if(A.isEmpty()) return -1;
        while(!A.isEmpty())
            B.addLast(A.removeLast());
        return B.removeLast();
    }
    */

    public static void main(String[] args) {
        CQueue09 cQueue = new CQueue09();
        System.out.println(cQueue.deleteHead());
        cQueue.appendTail(5);
        cQueue.appendTail(2);
        System.out.println(cQueue.deleteHead());
        System.out.println(cQueue.deleteHead());
    }
}
