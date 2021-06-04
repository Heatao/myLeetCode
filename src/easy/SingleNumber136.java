package easy;

/**
 * 要求O(n)和不使用额外空间
 * 这条要求就暗示了要么用位运算，要么用双指针
 */
public class SingleNumber136 {
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int num : nums)
            ans = ans ^ num;
        return ans;
    }
}
