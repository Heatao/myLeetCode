package easy;

/***
 * LeetCode26.删除排序数组中的重复项
 * 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 *
 * 说明:
 * 为什么返回数值是整数，但输出的答案是数组呢?
 * 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 * 你可以想象内部操作如下:
 * // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
 * int len = removeDuplicates(nums);
 * // 在函数里修改输入数组对于调用者是可见的。
 * // 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
 * for (int i = 0; i < len; i++) {
 *     print(nums[i]);
 * }
 */
public class RemoveDuplicates26 {
    /**
     * 数组真的是以引用传递的吗？
     * 复杂度要求O(1)的意思是：耗时/耗空间与输入数据大小无关，而不是只能用一个变量的意思。
     * @param nums 数组
     * @return 长度
     */
    public int mySolution_removeDuplicates(int[] nums) {
        if (nums.length < 2) return nums.length;

        int preNum = nums[0];
        int numPos = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != preNum){
                preNum = nums[i];
                nums[numPos] = nums[i];
                numPos ++;
            }
        }
        return numPos;
    }

    /**
     * 前后双指针法
     */
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }
}
