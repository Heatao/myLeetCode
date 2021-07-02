package medium;

import java.util.*;
import java.util.stream.Collectors;

/**
 * LeetCode15.三数之和
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 */
public class ThreeSum15 {
    /**
     * 第一反应是哈希表，考虑有无数据结构可以解决该问题，栈，堆，链表皆不可。
     * 考虑是否所有的数都需要被访问到。
     * 考虑最暴力的解法，O(n^3)，根据正负去除一定的冗余，再加上哈希表。
     * 后来发现不可以用hash表，因为可能与前两个数用到了nums中的同一个数
     *
     * 下面这个代码执行时间超出了...
     * @param nums
     * @return
     */
    public static List<List<Integer>> mySolution_threeSum(int[] nums) {
        List<Integer> num_list = Arrays.stream(nums).boxed().sorted().collect(Collectors.toList());
        int zeroIndex = 0;
        for (int i = 1; i < num_list.size()-1; i++) {
            if (num_list.get(i-1) < 0 && num_list.get(i+1) > 0){
                zeroIndex = i;
                break;
            }
        }
        List<List<Integer>> answer_list = new LinkedList<>();
//        Set<Integer> nums_set = new HashSet<>(num_list);
        int lasti = Integer.MIN_VALUE;
        int lastj = Integer.MIN_VALUE;
        int lastc = Integer.MIN_VALUE;
        for (int i = 0; i < num_list.size(); i++) {
            if (num_list.get(i) == lasti) continue;
            lasti = num_list.get(i);
            for (int j = i+1; j < num_list.size() && num_list.get(i)+ num_list.get(j) <= 0; j++) {
                if (lastj == num_list.get(j)) continue;
                lastj = num_list.get(j);
//                Integer c = -(num_list.get(i)+ num_list.get(j));
////                if (nums_set.contains(c)){
////                    answer_list.add(Arrays.asList(num_list.get(i), num_list.get(j), c));
////                }
                for (int c = Math.max(j+1, zeroIndex); c < num_list.size(); c++){
                    if (lastc == num_list.get(c)) continue;
                    lastc = num_list.get(c);
                    if (num_list.get(i) + num_list.get(j) + num_list.get(c) == 0){
                        answer_list.add(Arrays.asList(num_list.get(i), num_list.get(j), num_list.get(c)));
                    }
                }
                lastc = Integer.MIN_VALUE;
            }
            lastj = Integer.MIN_VALUE;
        }
        return answer_list;
    }

    /**
     * 和上面的代码是一样的，我以为这种方式和双指针是一样的，但实际还是超时了
     * @param nums
     * @return
     */
    public static List<List<Integer>> mySolution2_threeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();

        int zeroIndex = 0;
        for (int i = 1; i < n-1; i++) {
            if (nums[i-1] < 0 && nums[i+1] > 0){
                zeroIndex = i;
                break;
            }
        }

        for (int first = 0; first < n; ++first) {
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            int target = -nums[first];
            // 枚举 b
            for (int second = first + 1; second < n; ++second) {
                // 需要和上一次枚举的数不相同
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                for (int third = Math.max(second+1, zeroIndex); third < n; ++third){
                    if (third > second + 1 && nums[third] == nums[third-1]) {
                        continue;
                    }
                    if (nums[second] + nums[third] == target){
                        List<Integer> list = new ArrayList<Integer>();
                        list.add(nums[first]);
                        list.add(nums[second]);
                        list.add(nums[third]);
                        ans.add(list);
                    }
                }//for3
            }//for2
        }//for1
        return ans;
    }

    /**
     * 双指针消除的冗余在于：上一次为0的第二个数和第三个数，下一个组合，第二个数增大了，第三个数就会减小，也就是说，第三个数不需要每次从second+1开始
     * @param nums
     * @return
     */
    public static List<List<Integer>> official_threeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        // 枚举 a
        for (int first = 0; first < n; ++first) {
            // 需要和上一次枚举的数不相同
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            // c 对应的指针初始指向数组的最右端
            int third = n - 1;
            int target = -nums[first];
            // 枚举 b
            for (int second = first + 1; second < n; ++second) {
                // 需要和上一次枚举的数不相同
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                // 需要保证 b 的指针在 c 的指针的左侧
                while (second < third && nums[second] + nums[third] > target) {
                    --third;
                }
                // 如果指针重合，随着 b 后续的增加
                // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1,-1,-1,0};
        System.out.println(mySolution_threeSum(nums));
    }

    private List<List<Integer>> do2nd(int[] nums) {

    }
}
