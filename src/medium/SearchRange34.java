package medium;

import java.util.Arrays;

/**
 * LeetCode34.在排序数组中查找元素的第一个和最后一个位置
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * 如果数组中不存在目标值，返回 [-1, -1]。
 */
public class SearchRange34 {
    /**
     * 要求logn，则不可以遍历所有元素
     * logn的算法主要有二分法，欧几里得算法（求最大公约数），幂函数
     * 我的思路：
     * 从最简单的想法做起，不想遍历所有，则用双端指针从两边开始遍历，但是这样最坏情况也是遍历所有
     * 如何加速遍历？从两端开始用2为底数开始增加间隔。这样的问题在于2^n，在后期会特别大，很容易直接跨过目标点，如果从绕过点开始再以2^n开始，
     * 则会再次出现相同情况，或许递归会好一点，但时间复杂度不好计算，边界也比较麻烦。
     *
     * 回到最初，直接用二分法做，题目已暗示logn
     */
    public static int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) return new int[]{-1, -1};
        //用两个二分法找到头和尾
        int s = dichotomyLeft(nums, target, 0, nums.length-1);
        if (s == -1){
            return new int[]{-1, -1};
        }
        int e = dichotomyRight(nums, target, 0, nums.length-1);
        return new int[]{s, e};
    }

    public static int dichotomyLeft(int[] nums, int target, int start, int end) {
        if (nums[start] > target || nums[end] < target || nums[end] < nums[start]) return -1;
        if (nums[start] == target)
            return start;

        int medium = (end+start) / 2;
        if (nums[medium] >= target){
            //优先判断左边部分
            return dichotomyLeft(nums, target, start, medium);
        }
        else return dichotomyLeft(nums, target, medium+1, end);
    }

    public static int dichotomyRight(int[] nums, int target, int start, int end) {
        if (nums[start] > target || nums[end] < target || nums[end] < nums[start]) return -1;
        if (nums[end] == target)
            return end;
        //下面的判断是为了防止当最后只剩两个数且左边的数为target，会陷入重复
        if (nums[start] == target && nums[start+1] != target)
            return start;

        int medium = (end+start) / 2;
        if (nums[medium] <= target){
            //优先判断右边部分
            return dichotomyRight(nums, target, medium, end);
        }
        else return dichotomyRight(nums, target, start, medium-1);
    }

    public static void main(String[] args) {
        int[] nums = {5,7,7,8,8,10};
        System.out.println(Arrays.toString(searchRange(nums, 8)));
    }
}
