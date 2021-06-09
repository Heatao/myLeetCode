package medium;

import hard.FindMin154;

import java.util.Arrays;

/**
 * 关键条件：任意一个，nums[-1] = nums[n] = -∞，每个值都不相同
 * 遍历的方法可以小小的优化，比如右边如果小于左边，则右边的下一个不用遍历了
 * 时间复杂度为 O(logN) 的方法就是在暗示用二分查找
 * 看了题解终于理解
 */
public class FindPeakElement162 {
    public int findPeakElement(int[] nums) {
        int len = nums.length;
        if (len == 1)
            return 0;
        if (len == 2)
            if (nums[1] > nums[0]) return 1;
            else return 0;

        int slow = 0;
        int high = len - 1;
        while (slow < high) {
            int mid = slow + (high - slow) / 2;
            if (mid -1 >= 0 && nums[mid] < nums[mid - 1]) {
                // 这里貌似-1与否不影响
                high = mid;
            }
            else if (mid + 1 < len && nums[mid] < nums[mid + 1]) {
                slow = mid + 1;
            }
            else return mid;
        }

        // 不出意外，不会访问到这里，才怪
        // 在l==r时,其实是没有判断当前是否就是答案, 但本题一定会有答案
        return slow;
    }

    public static void main(String[] args) {
        FindPeakElement162 findPeakElement162 = new FindPeakElement162();
        int[] nums1 = {3,1,2};
        int[] nums2 = {1,2,3};
        int[] nums3 = {1,2,3,1};
        int[] nums4 = {1,2,1,3,5,6,4};
        System.out.println(findPeakElement162.findPeakElement(nums4));
    }
}
