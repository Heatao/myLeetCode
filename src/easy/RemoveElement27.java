package easy;

/**
 * LeetCode27.移除元素
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 */
public class RemoveElement27 {
    public static int mySolution_removeElement(int[] nums, int val) {
        int pos = 0;
        for (int i = 0; i < nums.length;) {
            if (nums[i] == val){
//                pos = i;根本不需要这一步
                while (i < nums.length && nums[i] == val) {
                    i++;
                }
            }
            else{
                nums[pos] = nums[i];
                pos ++;
                i++;
            }
        }
        for (int i = 0; i < pos; i++) {
            System.out.println(nums[i]);
        }
        return pos;
    }

    public static void main(String[] args) {
        int[] nums = {0,1,2,2,3,0,4,2};
        System.out.println(mySolution_removeElement(nums, 2));
    }
}
