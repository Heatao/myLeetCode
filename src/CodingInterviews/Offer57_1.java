package CodingInterviews;

import java.util.Arrays;

public class Offer57_1 {
    /*
    朴素的想法，遍历数组得到第一个数，另一个数用二分查找得到。
    看了题解发现可以通过 HashMap 或 双指针 来做。
     */
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length <= 1) return null;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int firstNum = nums[i];
            int secondNum = bisect(nums, i, len, target - firstNum);
            if (firstNum + secondNum == target) return new int[]{firstNum, secondNum};
        }
        return null;
    }

    // 左闭右开
    private int bisect(int[] nums, int first, int last, int target) {
        if (first > last) return first;
        while (first < last) {
            int mid = first + (last - first) / 2;
            if (nums[mid] < target)
                first = mid + 1;
            else last = mid;
        }

        // 一般而言二分查找返回的是index，所以这里返回值可能会遇到超过范围的情况
        if (first < nums.length)
            return nums[first];
        else return Integer.MIN_VALUE;
    }

    public static void main(String[] args) {
        Offer57_1 offer57 = new Offer57_1();
        int[] nums1 = {14,15,16,22,53,60};
        int[] nums2 = {10,26,30,31,47,60};
        int target1 = 76;
        int target2 = 40;
        System.out.println(Arrays.toString(offer57.twoSum(nums1, target1)));
        System.out.println(Arrays.toString(offer57.twoSum(nums2, target2)));
    }
}
