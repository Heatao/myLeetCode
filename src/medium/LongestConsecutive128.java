package medium;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 这个题一开始我也想到用Hash了，但是我的想法是来一个放进Hash表一个，然后再找其+1和-1的，这样不对！
 */
public class LongestConsecutive128 {
    public int longestConsecutive(int[] nums) {
        // 优化：如果Hash表存在当前数字-1的，那么它铁定不是左侧的边界
        if (nums.length <= 0)
            return 0;
        int longest = 0;
        // 因为不是封装类型的数组，所以不能Arrays.asList(nums)再传入HashSet，只能遍历
        HashSet<Integer> numsHash = new HashSet<>();
        for (Integer num : nums) {
            numsHash.add(num);
        }

        for (Integer num : nums) {
            if (numsHash.contains(num-1))
                continue;
            int temp = 1;
            while (numsHash.contains(num+1)) {
                temp++;
                num += 1;
            }
            longest = Math.max(longest, temp);
        }

        return longest;
    }
}
