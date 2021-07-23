package test;

/**
 * 快排有多种写法，下面是填坑法，也是我在本科学到的方法
 * 其他方法参见：https://segmentfault.com/a/1190000004410119
 */
public class QuickSort {
    public void quickSort(int[] nums, int start, int end){                          // 左闭右闭喔！
        if(start >= end){
            return;
        }
        int mid = partition(nums, start, end);
        quickSort(nums, start, mid-1);
        quickSort(nums, mid+1, end);
    }

    private int partition(int[] nums, int start, int end){
        int pivot = nums[start];
        while(start < end){
            while(nums[end] >= pivot && start < end) end--;
            nums[start] = nums[end];
            // 如果存在重复元素的话下面需要写<=
            while(nums[start] < pivot && start < end) start++;
            nums[end] = nums[start];
        }
        nums[start] = pivot;
        return start;
    }
}
