package test;

/**
 * 二分查找
 */
public class BisectLeft {
    // 求非降序范围[first, last)内第一个不小于value的值的位置
    public int lower_bound(int[] nums, int first, int last, int value) {
        while (first < last) {
            int mid = first + (last - first) / 2;
            if (nums[mid] < value)
                first = mid + 1;
            else last = mid;
        }
        return first;
    }

    // 上面这种做法是左闭右开的，但是很多时候要求右也是闭的，比如153题
    public int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int pivot = low + (high - low) / 2;
            // 注意这里一定要和高位相比！
            if (nums[pivot] < nums[high]) {
                high = pivot;
            } else {
                low = pivot + 1;
            }
        }
        return nums[low];
    }

}
