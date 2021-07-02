package medium;

import java.util.Arrays;

public class SortArray912 {
    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length-1);              // 左闭右闭
        return nums;
    }

    private void quickSort(int[] nums, int left, int right) {
        if (left > right) return;
        int pivot = partition(nums, left, right);
        quickSort(nums, left, pivot-1);                     // 这里很容易忽略
        quickSort(nums, pivot+1, right);
    }

    private int partition(int[] nums, int left, int right) {
        int retain = nums[left];
        while (left < right) {
            while (retain <= nums[right] && left < right) right--;
            nums[left] = nums[right];
            while (nums[left] < retain && left < right) left++;
            nums[right] = nums[left];
        }
        nums[left] = retain;
        return left;
    }

    public static void main(String[] args) {
        SortArray912 sortArray912 = new SortArray912();
        int[] nums = {5,1,1,2,0,0};
        System.out.println(Arrays.toString(sortArray912.sortArray(nums)));
    }
}
