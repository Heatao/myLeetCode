package easy;

/**
 * 几年前做过为数不多还记得的题目
 */
public class MajorityElement169 {
    public int majorityElement(int[] nums) {
        if (nums.length == 1)
            return nums[0];
        int ans = nums[0];
        int count = 1;
        // 我是沙雕，nums.length根本不需要-1
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                ans = nums[i];
                count = 1;
                continue;
            }

            if (ans == nums[i]) {
                count++;
            }
            else count--;
        }

        return ans;
    }

    public static void main(String[] args) {
        MajorityElement169 majorityElement169 = new MajorityElement169();
        int[] nums = {2,2,1,1,1,2,2};
        System.out.println(majorityElement169.majorityElement(nums));
    }
}
