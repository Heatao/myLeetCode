package CodingInterviews;

import java.util.LinkedList;

/**
 * 请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。
 * 若队列为空，pop_front 和 max_value 需要返回 -1
 *
 * 没做出来，抄的题解，必须掌握的一道题！！！
 * 单调队列来做滑动窗口问题是常用的方法，上道题也可以这样做
 */
public class MaxQueue59_2 {

    LinkedList<Integer> maxQueue;
    LinkedList<Integer> intQueue;

    public MaxQueue59_2() {
        maxQueue = new LinkedList<>();
        intQueue = new LinkedList<>();
        maxQueue.add(Integer.MIN_VALUE);
    }

    public int max_value() {
        if (maxQueue.isEmpty()) return -1;
        return maxQueue.peekFirst();              // LinkedList的peek方法返回的是头部元素喔
    }

    public void push_back(int value) {
        while (!maxQueue.isEmpty() && maxQueue.peekLast() < value)
            maxQueue.pollLast();
        maxQueue.addLast(value);
        intQueue.addLast(value);
    }

    public int pop_front() {
        if (intQueue.isEmpty()) return -1;
        int ans = intQueue.poll();
        if (ans == maxQueue.peekFirst())
            maxQueue.pollFirst();
        return ans;
    }

}
