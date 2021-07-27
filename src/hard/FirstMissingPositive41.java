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
        int[] nums1 = {1,2,0};
        int[] nums2 = {3,4,-1,1};
        int[] nums3 = {7,8,9,11,12};
        int[] nums4 = {2,2,4,0,1,3,3,3,4,3};
        int[] nums5 = {43,20,39,-7,-8,-2,8,17,10,17,12,6,37,17,50,44,3,11,18,-4,44,37,28,50,15,50,19,0,45,5,37,35,35,21,39,35,27,-8,-1,47,19,22,1,1,47,-4,-7,-3,16,21,2,7,6};
//        System.out.println(firstMissingPositive41.firstMissingPositive(nums));
//        System.out.println(firstMissingPositive41.do2nd_firstMissingPositive(nums));
//        System.out.println(firstMissingPositive41.do2nd_firstMissingPositive(nums1));
//        System.out.println(firstMissingPositive41.do2nd_firstMissingPositive(nums2));
//        System.out.println(firstMissingPositive41.do2nd_firstMissingPositive(nums3));
//        System.out.println(firstMissingPositive41.do2nd_firstMissingPositive(nums4));
        System.out.println(firstMissingPositive41.do2nd_firstMissingPositive(nums5));
    }

    /**
     * 即便修改了很久，下面的代码依然是存在错误的，因为只要left的数小于0就不可以交换了，这样right本应放的位置没有放过去，究其原因，就不应该用while递归的去交换
     * @param nums
     * @return
     */
    public int do2nd_firstMissingPositive(int[] nums) {
        // 要求时间复杂度为O(n)，并且常数级额外空间，也就是说用排序和栈都不可
        // 结合数组下标来进行哈希
        if(nums == null || nums.length == 0) return 1;
        int len = nums.length;
        for(int i = 0; i < len; i++) {
            if(nums[i] == i+1 || nums[i] > len || nums[i] <= 0) continue;
            // cur表示递归更新的下标哟
            int cur = i;
            // 下面这个while循环中坑很多
            while(cur >= 1 && cur < len && nums[cur] < len && nums[cur] >= 1 && nums[cur] != cur+1) {
                int tmp = nums[nums[cur]-1];
                // 这里必须判断是否一执，如果这里不用哦那个while的方法的话就可以不用
                if (tmp == nums[cur]) break;
                nums[nums[cur]-1] = nums[cur];
                nums[cur] = tmp;
                // 继续更新cur
                cur = nums[cur];
            }
        }

        for(int i = 0; i < len; i++) {
            if(nums[i] != i+1) return i+1;
        }
        return len+1;
    }
}
/**
 * G655 9.43-15.23
 * G3289 4.01-19.41
 **/
