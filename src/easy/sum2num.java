package easy;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode1.两数之和
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 *
 * 注意：没有说num两个数不能是一样的，而且可能一大一小
 * */
public class sum2num {
    public int[] mySolutin_twoSum(int[] nums, int target) {
        /**
         * 我的解法：暴力破解
         * */
        for (int i=0; i<nums.length; i++){
            for (int j=i+1; j<nums.length; j++){
                if (nums[i]+nums[j] == target){
                    int[] aim = new int[] {i, j};
                    return aim;
                }
            }
        }
        return null;
    }

    public int[] officialSolution_twoSum(int[] nums, int target) {
        /**
         * 使用hash表
         * 思路：把nums的每一个数thisNum放进哈希表，key就是thisNum的值，value是索引，
         * 放进哈希表的同时，寻找表里是否存在target-thisNum
         * 优化思路只需要遍历一次哈希表，边插入边判断
         * */
        Map<Integer, Integer> map = new HashMap<>();
        for (int i=0; i<nums.length; i++){
            Integer firstNum = map.get(target-nums[i]);
            if (firstNum != null){
                return new int[] {firstNum, i};
            }
            map.put(nums[i], i);
        }
        return null;
    }

}
