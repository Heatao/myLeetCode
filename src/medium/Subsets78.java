package medium;

import java.util.ArrayList;
import java.util.List;

public class Subsets78 {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> combinations = new ArrayList<>();
        List<Integer> tempList = new ArrayList<>();
        backTrack(combinations, tempList, 0, nums);
        return combinations;
    }

    private void backTrack(List<List<Integer>> combinations, List<Integer> tempList, int start, int[] nums) {
        combinations.add(new ArrayList<>(tempList));

        for (int i = start; i < nums.length; i++) {
            tempList.add(nums[i]);
            backTrack(combinations, tempList, i+1, nums);
            tempList.remove(tempList.size()-1);
        }
    }
}
