package easy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TwoSum167 {
    /*
    最朴素的想法，用哈希表，但是利用哈希表其实没有利用到有序数组的性质
    官方题解给出可以用二分查找的方法，这样的时间复杂度是O(nlogn)
    还可以用双指针的做法，比较巧妙
     */
    public int[] twoSum(int[] numbers, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] ans = new int[2];
        for (int i = 0; i < numbers.length; i++) {
            int thisNum = numbers[i];
            if (map.containsKey(target - thisNum)) {
                ans[0] = map.get(target - thisNum);
                ans[1] = i+1;
                return ans;
            }
            else map.put(thisNum, i+1);
        }
        return null;
    }

    public int[] official_twoSum(int[] numbers, int target) {
        int low = 0, high = numbers.length - 1;
        while (low < high) {
            int sum = numbers[low] + numbers[high];
            if (sum == target) {
                return new int[]{low + 1, high + 1};
            } else if (sum < target) {
                ++low;
            } else {
                --high;
            }
        }
        return new int[]{-1, -1};
    }
}
