package CodingInterviews;

/**
 * 找出数组中任意一个重复的数字
 */
public class Offer03 {
    /**
     * 当然还可以用set
     */
    public int findRepeatNumber(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int len = nums.length;
        int aim;
        for (int i = 0; i < len; i++) {
            aim = nums[i];
            for (int j = i+1; j < len; j++) {
                if (aim == nums[j])
                    return aim;
            }
        }

        return nums[0];
    }

    /**
     * 原地交换模拟字典，很精妙
     * https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/solution/mian-shi-ti-03-shu-zu-zhong-zhong-fu-de-shu-zi-yua/
     */
    public int others_findRepeatNumber(int[] nums) {
        int i = 0;
        while(i < nums.length) {
            if(nums[i] == i) {
                i++;
                continue;
            }
            if(nums[nums[i]] == nums[i]) return nums[i];
            int tmp = nums[i];
            nums[i] = nums[tmp];
            nums[tmp] = tmp;
        }
        return -1;
    }

}
