package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 * LeetCode47.全排列2
 */
public class PermuteUnique47 {
    public List<List<Integer>> mySolution_permuteUnique(int[] nums) {
        List<List<Integer>> combinationsList = new ArrayList<>();
        List<Integer> oneCombination = new ArrayList<>();
        Arrays.sort(nums);

        boolean[] numsUsed = new boolean[nums.length];
        arrange(nums, numsUsed, combinationsList, oneCombination);
        return combinationsList;
    }

    private void arrange(int[] nums, boolean[] used, List<List<Integer>> combinationsList, List<Integer> oneCombination) {
        if (oneCombination.size() == nums.length){
            combinationsList.add(new ArrayList<>(oneCombination));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            //关键是!used[i - 1]
            if (i > 0 && nums[i] == nums[i-1] && !used[i - 1])
                continue;
            if (!used[i]) {
                oneCombination.add(nums[i]);
                used[i] = true;
                arrange(nums, used, combinationsList, oneCombination);

                oneCombination.remove(oneCombination.size() - 1);
                used[i] = false;
            }
        }
    }
}
