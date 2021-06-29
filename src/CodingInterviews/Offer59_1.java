package CodingInterviews;

import java.util.Deque;
import java.util.LinkedList;

public class Offer59_1 {
    /*
    我的思路：
    最暴力的解法是每次判断滑动窗口内的k个值的最大值，但是这样存在冗余
    所以可以记录当前的窗口的最大值和位置，当这个最大值不在下一个窗口的时候再重新计算，在的话用这个最大值和新的一位判断是否需要更新最大值
    靠，做成最小值了

    看官方题解是用优先队列或者单调队列来做的，感觉我这个方法也不错，卜老师说的先用最笨的办法，然后找冗余，一步步优化
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) return new int[]{};
        int left = 0, right = k-1;
        int max, maxIndex;
        maxIndex = findMax(nums, left, right);
        max = nums[maxIndex];

        int[] numList = new int[nums.length - k + 1];
        numList[0] = max;
        int index = 1;
        for (int i = 1; i <= nums.length - k; i++) {
            left = i;
            right = left + k - 1;
            if (maxIndex < left) {
                maxIndex = findMax(nums, left, right);
                max = nums[maxIndex];
            }
            if (max <= nums[right]) {
                max = nums[right];
                maxIndex = right;
            }
            numList[index] = max;
            index++;
        }
        return numList;
    }

    private int findMax(int[] nums, int left, int right) {
        int max = nums[left], maxIndex = left;
        for (int i = left+1; i <= right; i++) {
            // 注意这里的等号
            if (max <= nums[i]) {
                max = nums[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public int[] official_maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> deque = new LinkedList<Integer>();
        for (int i = 0; i < k; ++i) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }

        int[] ans = new int[n - k + 1];
        ans[0] = nums[deque.peekFirst()];
        for (int i = k; i < n; ++i) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            while (deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }
            ans[i - k + 1] = nums[deque.peekFirst()];
        }
        return ans;
    }

}
