package CodingInterviews;

import java.util.PriorityQueue;

/**
 * 我自己的思路是addNum的时候用一个二分查找，找到应该放入list的位置
 * 看了题解发现用优先队列很好做。优先队列可以用堆实现
 * 由于队列不方面获取中间的元素，所以这里可以采用两个队列各存储一半
 * https://leetcode-cn.com/problems/shu-ju-liu-zhong-de-zhong-wei-shu-lcof/solution/mian-shi-ti-41-shu-ju-liu-zhong-de-zhong-wei-shu-y/
 */
public class MedianFinder41 {

    private PriorityQueue<Integer> minHeap;
    private PriorityQueue<Integer> maxHeap;

    /** initialize your data structure here. */
    public MedianFinder41() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>((x, y) -> (y - x));             // 这里传入的是Comparator
    }

    public void addNum(int num) {
        if (minHeap.size() == maxHeap.size()) {
            maxHeap.add(num);
            minHeap.add(maxHeap.poll());
        }
        else {
            minHeap.add(num);
            maxHeap.add(minHeap.poll());
        }
    }

    public double findMedian() {
        return minHeap.size() != maxHeap.size() ? minHeap.peek() : (minHeap.peek() + maxHeap.peek()) / 2.0;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */