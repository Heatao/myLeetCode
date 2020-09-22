package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode16.最接近的三数之和
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。
 * 找出 nums 中的三个整数，使得它们的和与 target 最接近。
 * 返回这三个数的和。假定每组输入只存在唯一答案。
 *
 * 示例：
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 *
 * 3 <= nums.length <= 10^3
 * -10^3 <= nums[i] <= 10^3
 * -10^4 <= target <= 10^4
 *
 */
public class threeSumClosest {
    /**
     * 第一反应可以利用的，hashmap，但实际不可行，DP？
     * 考虑是否需要遍历全部组合？貌似需要的 如何去冗余 -> 利用排序
     * 第三个数的冗余，前两个数固定情况下，第三个数的指针往左，如果不是"接近"target，那就跳出循环
     * 保持第三个数的位置
     * 第二个数再右移，第二个数的范围是1和3数之间
     * 第一个数再右移
     * 感觉不是全局最优啊
     *
     * 反思：想到了排序，想到了双指针，但是当我用abs的时候，就已经输了
     * @param nums
     * @param target
     * @return
     */
    public static int mySolution_threeSumClosest(int[] nums, int target) {
//        Arrays.sort(nums);
//        int first = 0;
//        int second = first + 1;
//        int third = nums.length - 1;
//        int ans = 0;
//
//        while (first < nums.length && first < second) {
//            while (second < nums.length && second < third){
//                while (third > second && Math.abs(target - nums[first] - nums[second] - nums[third]) < target - ans) {
//                    ans = nums[first] + nums[second] + nums[third];
//                    third --;
//                }
//                if (second == third){
//                    System.out.println("2==3，不可能吧");
//                }
//                if (Math.abs(target - nums[first] - nums[second+1] - nums[third]) < target - ans){
//                    ans = nums[first] + nums[second+1] + nums[third];
//                    second ++;
//                }
//                else if (Math.abs(target - nums[first] - nums[second-1] - nums[third]) < target - ans){
//                    ans = nums[first] + nums[second-1] + nums[third];
//                    second --;
//                }
//            }
//            if (second == third){
//                System.out.println("1==2，不可能吧");
//            }
//            if (Math.abs(target - nums[first] - nums[second] - nums[third]) < target - ans){
//                ans = nums[first] + nums[second] + nums[third];
//                first ++;
//            }
//        }
        Arrays.sort(nums);
        int ans = Integer.MAX_VALUE;
        for (int first = 0; first < nums.length; ++first) {
            // 需要和上一次枚举的数不相同
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            // c 对应的指针初始指向数组的最右端
            int third = nums.length - 1;
            // 枚举 b
            for (int second = first + 1; second < nums.length; ++second) {
                // 需要和上一次枚举的数不相同
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                // 需要保证 b 的指针在 c 的指针的左侧
                while (second+1 < third &&
                        Math.abs(target - nums[first] - nums[second] - nums[third-1]) < Math.abs(target - ans)) {
                    ans = nums[first] + nums[second] + nums[third-1];
                    --third;
                }
                while (third < nums.length-1 &&
                        Math.abs(target - nums[first] - nums[second] - nums[third+1]) < Math.abs(target - ans)) {
                    ans = nums[first] + nums[second] + nums[third+1];
                    ++third;
                }
                // 如果指针重合，随着 b 后续的增加
                // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                if (second == third) {
                    break;
                }
            }
        }
        return ans;
    }

    /**
     * 根据第一个数的循环来初始化第二个数和第三个数
     * 第二个数和第三个数是剩下数最远的地方，那么就可以让这两个数都只移动一边，这就是双指针快一点的原因！！！
     * @param nums
     * @param target
     * @return
     */
    public int official_threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int best = 10000000;

        // 枚举 a
        for (int i = 0; i < n; ++i) {
            // 保证和上一次枚举的元素不相等
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 使用双指针枚举 b 和 c
            int j = i + 1, k = n - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                // 如果和为 target 直接返回答案
                if (sum == target) {
                    return target;
                }
                // 根据差值的绝对值来更新答案
                if (Math.abs(sum - target) < Math.abs(best - target)) {
                    best = sum;
                }
                if (sum > target) {
                    // 如果和大于 target，移动 c 对应的指针
                    int k0 = k - 1;
                    // 移动到下一个不相等的元素
                    while (j < k0 && nums[k0] == nums[k]) {
                        --k0;
                    }
                    k = k0;
                } else {
                    // 如果和小于 target，移动 b 对应的指针
                    int j0 = j + 1;
                    // 移动到下一个不相等的元素
                    while (j0 < k && nums[j0] == nums[j]) {
                        ++j0;
                    }
                    j = j0;
                }
            }
        }
        return best;
    }

    public static void main(String[] args) {
        int[] nums = {0, 2, 1, -3};
        System.out.println(mySolution_threeSumClosest(nums, 1));
    }
}
