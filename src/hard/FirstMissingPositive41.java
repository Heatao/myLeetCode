package hard;

/**
 * LeetCode41.缺失的第一个正数
 * 给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。
 */
public class FirstMissingPositive41 {
    /**
     * 这个题如果可以用hash是非常简单的。
     * 看了题解说是要原地hash，我想如果给定数组的值都超过其长度不就不可以了嘛
     * 但其实是陷入了定势思维，进一步：根本不会造成影响，虽然那时候缺失不可以原地hash了，但是答案在前面就会出现
     * 参考：https://leetcode-cn.com/problems/first-missing-positive/solution/tong-pai-xu-python-dai-ma-by-liweiwei1419/
     */
    public int firstMissingPositive(int[] nums) {
        int index = 0;
        while(index < nums.length) {
            if (nums[index] > 0 && nums[index] != index+1
                    && nums[index] < nums.length && nums[nums[index]-1] != nums[index]) {    //  注意这里要==+1啊，并且要防止相等
                int tmp = nums[nums[index] - 1];
                nums[nums[index] - 1] = nums[index];
                nums[index] = tmp;
            }else {
                index++;
            }
        }//while

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != (i+1))
                return i+1;
        }
        //最后都存在才会返回这个
        return nums.length+1;
    }

    public static void main(String[] args) {
        FirstMissingPositive41 firstMissingPositive41 = new FirstMissingPositive41();
        int[] nums = {1,1};
        System.out.println(firstMissingPositive41.firstMissingPositive(nums));
    }
}
