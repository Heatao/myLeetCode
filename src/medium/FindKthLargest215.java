package medium;

import java.util.PriorityQueue;

/**
 * 可以用堆实现，也可以用快排实现
 */
public class FindKthLargest215 {
    public int findKthLargest(int[] nums, int k) {
        if (k > nums.length) throw new RuntimeException("Incorrect input data.");
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((x,y)->(y-x));
        for (Integer num : nums) {
            priorityQueue.offer(num);
        }
        for (int i = 0; i < k-1; i++) {
            priorityQueue.poll();
        }
        return priorityQueue.poll();
    }

    public static void main(String[] args) {
        FindKthLargest215 findKthLargest215 = new FindKthLargest215();
        int[] nums = {3,2,3,1,2,4,5,5,6};
        System.out.println(findKthLargest215.findKthLargest(nums, 4));
    }
}
