package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 注意：解集 不能 包含重复的子集。
 * 第一个想法是将nums排序，然后判断当前的数字是否需要入tempList，但是这样对于122也不好处理
 * 要解决这个问题，就如leetcode官方所言：若发现没有选择上一个数，且当前数字与上一个数相同，则可以跳过当前生成的子集。
 * 对于122，第一个2被选了所以可以呀
 *
 * 第二个想法是用set，但是set对于外层List是否有效呢
 */
public class SubsetsWithDup90 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> combinations = new ArrayList<>();
        List<Integer> tempList = new ArrayList<>();
        Arrays.sort(nums);

        combinations.add(new ArrayList<>(tempList));
        backtrack(nums, combinations, tempList, 0);
        return combinations;
    }

    private void backtrack(int[] nums, List<List<Integer>> combinations, List<Integer> tempList, int start) {
        for (int i = start; i < nums.length; i++) {
            //下面这里非常精妙，i > start，而非i-1 >= 0
            //i > start保证了122的第二个2也会被放进tempList
            //要理解，因为递归的存在，所以每一层的backtrack其实是保证了固定数字的哦，也就是说，只要保证这一层的for循环不会挑到重复的就行啦，好好理解！！！
            if (i > start && nums[i] == nums[i-1])
                continue;
            tempList.add(nums[i]);
            combinations.add(new ArrayList<>(tempList));
            backtrack(nums, combinations, tempList, i+1);
            tempList.remove(tempList.size()-1);
        }
    }

    public static void main(String[] args) {
        SubsetsWithDup90 subsetsWithDup90 = new SubsetsWithDup90();
        int[] nums = {1,2,2};
        for (List<Integer> each : subsetsWithDup90.subsetsWithDup(nums)) {
            System.out.println(each.toString());
        }
    }
}
