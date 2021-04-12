package medium;

/**
 * leetcode33有同学这样评论：
 * 将数组一分为二，其中一定有一个是有序的，另一个可能是有序，也能是部分有序。
 * 此时有序部分用二分法查找。无序部分再一分为二，其中一个一定有序，另一个可能有序，可能无序。就这样循环.
 *
 * 我觉得很有误导，想一想为什么可以二分查找？
 * 因为有序的排列可以减少搜索空间。
 * 那么旋转之后可以吗？可以的，只要有一边有序也可以判断target在不在，可以二分查找，就是酱紫～
 */
public class Search81 {
    public boolean search(int[] nums, int target) {
        int first = 0;
        int last = nums.length - 1;
        int mid;
        while (first < last) {
            mid = first + (last - first) / 2;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[first] == nums[mid] && nums[mid] == nums[last]) {
                ++first;
                --last;
                continue;
            }
            if (nums[first] <= nums[mid]) {                 //左闭右开！
                if (nums[first] <= target && target < nums[mid]) {
                    last = mid - 1;
                }else first = mid + 1;
            }
            else {
                if (nums[mid] < target && target <= nums[last]) {
                    first = mid + 1;
                }
                else last = mid - 1;
            }
        }
        return nums[first] == target;           //这里其实可以直接return false
    }

    public static void main(String[] args) {
//        int[] nums = {2,5,6,0,0,1,2};
//        int[] nums = {4,5,6,7,0,1,2};
//        int[] nums = {1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1};
        int[] nums = {3,1};
        Search81 search81 = new Search81();
        System.out.println(search81.search(nums, 1));
    }
}
