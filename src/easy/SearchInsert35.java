package easy;

/**
 * LeetCode35.搜索插入位置
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 你可以假设数组中无重复元素。
 */
public class SearchInsert35 {
    /**
     * 典型二分查找
     * 试一试不用递归吧
     */
    public static int mySolution_searchInsert(int[] nums, int target) {
        int start = 0;
        int end = nums.length-1;
        int aimIndex = 0;
        while (start <= end) {
            if (nums[start] == target) return start;
            if (nums[start] <= target && target <= nums[end]){
                if (end-start==1) return end;
                int medium = start + (end - start) / 2;
                //我这里没有medium+1或medium-1，所以只剩两个数字时会陷入无限循环，需要单独判断只剩两个数字时
                if (nums[medium] <= target)
                    start = medium;
                else end = medium;
            }
            else if (nums[end] < target){
                return end+1;
            }
            else {
                return start;
            }
        }
        return aimIndex;
    }

    /**
     * while(left <= right) 这种写法表示在循环体内部直接查找元素；
     * 退出循环的时候 left 和 right 不重合，区间 [left, right] 是空区间。
     *
     * while(left < right) 这种写法表示在循环体内部排除元素；
     * 退出循环的时候 left 和 right 重合，区间 [left, right] 只剩下成 11 个元素，这个元素 有可能 就是我们要找的元素。
     *
     * 使用思路 2 完成「力扣」第 35 题
     * 思路分析：
     * 首先，插入位置有可能在数组的末尾（题目中的示例 3），需要单独判断。如果在数组的末尾，插入位置的下标就是数组的长度；
     * 否则，根据示例和暴力解法的分析，插入位置的下标是 大于等于 target 的第 11 个元素的位置。
     * 因此，严格小于 target 的元素一定不是解，在循环体中将左右边界 left 和 right 逐渐向中间靠拢，最后 left 和 right 相遇，则找到了插入元素的位置。根据这个思路，可以写出如下代码。
     *
     * 这种方式比我自己写的好，因为只判断了一次target可能出现在右边的情况，并且我很容易陷入无限循环，跳出的方式却要求每次循环都要额外判断一次
     *
     * 来源https://leetcode-cn.com/problems/search-insert-position/solution/te-bie-hao-yong-de-er-fen-cha-fa-fa-mo-ban-python-/
     */
    public int others_searchInsert(int[] nums, int target) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }

        // 特判
        if (nums[len - 1] < target) {
            return len;
        }
        int left = 0;
        int right = len - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            // 严格小于 target 的元素一定不是解
            if (nums[mid] < target) {
                // 下一轮搜索区间是 [mid + 1, right]
                left = mid + 1;
            } else {
                // 下一轮搜索区间是 [left, mid]
                right = mid;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int[] nums = {1,3,5,6};
        System.out.println(mySolution_searchInsert(nums, 2));
    }
}
