package medium;

import java.util.Arrays;

/**
 * LeetCode31.下一个排列
 * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * 必须原地修改，只允许使用额外常数空间。
 */
public class NextPermutation31 {
    /**
     * 更长的例子：1,5,8,4,7,6,5,3,1 -> 1，5，8，5，1，3，4，6，7
     * 字典序为数组从左到右排列成一个数组
     * 我认为下一个排列需要满足：
     * 尽可能改动数组右边的数字，把一个最小的大数往左边移动最小的幅度，然后其右边的数从小到大排列
     * 策略为，数组右边部分找到一个从大到小的排列，从降序排列中找到大于该排列左边的第一个数的一个数字，移动到其前面，右边再升序排列（注意这里交换后，右边应该是一个降序，所以可以直接reverse）
     * 如果整个数组为降序排列，则修改为升序排列
     */
    public void mySolution_nextPermutation(int[] nums) {
        int numsLen = nums.length;
        int leftNum = 0;
        int leftIndex = numsLen;
        for (int i=numsLen-1; i>0; i--){
            if (nums[i] > nums[i-1]){
                leftNum = nums[i-1];
                leftIndex = i-1;
                break;
            }
        }
        //交换left和其右边升序序列的数，这里仍然需要从右向左
        if (leftIndex != numsLen) {
            for (int i = numsLen-1; i > leftIndex; i--) {
                if (nums[i] > leftNum){
                    int tmp = nums[i];
                    nums[i] = leftNum;
                    nums[leftIndex] = tmp;
                    break;
                }
            }
        }
        else {
            swapArray(nums, 0, numsLen-1);
        }
        //右边的序列改为降序
        if (leftIndex+1 < numsLen){
//            for (int start=leftIndex+1, end=numsLen-1; start<end; start++, end--){
//                int temp = nums[end];
//                nums[end] = nums[start];
//                nums[start] = temp;
//            }
            swapArray(nums, leftIndex+1, numsLen-1);
        }
    }

    public void swapArray(int[] nums, int leftIndex, int rightIndex) {
        for (int start=leftIndex, end=rightIndex; start<end; start++, end--){
            int temp = nums[end];
            nums[end] = nums[start];
            nums[start] = temp;
        }
    }

    public void do2nd_nextPermutation(int[] nums) {
        // 规则：左右数字交换，左数尽可能接近右侧边界
        // 这个规则是错误的哟
        // 还应该加上，找到这个数之后，与左边最小的数字交换，然后左边数字的右侧全部重排
        int len = nums.length;
        for(int i = len; i >= 0; i--) {
            for(int j = len - 1; j > i; j--) {
                if(nums[i] < nums[j]) {
                    do2nd_swap(nums, i, j);
                    do2nd_swapAll(nums, i+1);
                    return;
                }
            }
        }
        Arrays.sort(nums);
    }

    private void do2nd_swap(int[] nums, int left, int right) {
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }

    private void do2nd_swapAll(int [] nums, int start) {
        int left = start, right = nums.length-1;
        while (left < right) {
            do2nd_swap(nums, left, right);
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        NextPermutation31 nextPermutation31 = new NextPermutation31();
        int[] nums = {1,1,5};
        int[] nums1 = {2,3,1};
        int[] nums2 = {1,3,2};
        nextPermutation31.do2nd_nextPermutation(nums2);
        for (int i = 0; i < nums2.length; i++) {
            System.out.println(nums2[i]);
        }
    }
}
