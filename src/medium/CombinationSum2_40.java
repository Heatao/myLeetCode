package medium;

import java.util.*;

/**
 * LeetCode40.组合总和2
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的每个数字在每个组合中只能使用一次。
 *
 * 说明：
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。 
 */
public class CombinationSum2_40 {
    /**
     * 和39的思路一样
     * 注意这道题没有39那种不包含重复元素，所有需要数组去重
     * 后来发现去重了会有错，因为允许用两次1，需要去重的是集合而非candidates
     * 我的想法是利用将list转字符串，在最初将candidates排序，得到的结果list就是一个从小到大的数字，可以排序成固定的key
     * 但是这样的操作在Java中除非使用封装好的StringUtils方法，不然会很麻烦
     * 最后是采用将每一个可能的结果转为数字来做，但是速度很慢
     */
    public List<List<Integer>> mySolution_combinationSum2(int[] candidates, int target) {
        List<List<Integer>> combinationsList = new ArrayList<>();
        List<Integer> combination = new ArrayList<>();
        List<Integer> list = new ArrayList<Integer>();

        //数组去重复
//        LinkedHashSet<Integer> candidatesSet = new LinkedHashSet<>();
//        for (Integer each : candidates){
//            candidatesSet.add(each);
//        }
        List<Integer> candicatesList = new ArrayList<>();
        for (Integer each : candidates) {
            candicatesList.add(each);
        }
        Collections.sort(candicatesList);

        Set<Integer> deduplicationSet = new LinkedHashSet<>();

        recurCombination(candicatesList, target, combinationsList, combination, 0, deduplicationSet);
        return combinationsList;
    }

    private void recurCombination(List<Integer> candidates, int remainTarget, List<List<Integer>> combinationsList,
                                  List<Integer> combination, int idx, Set<Integer> deduplicationSet) {
        if (remainTarget == 0) {
//            combinationsList.add(new ArrayList<>(combination));
//            StringUtils.join(combination.toArray(), separator);
            int tmp10 = 1;
            int key = 0;
            for (int i = combination.size()-1; i >= 0; i--) {
                key = key + combination.get(i) * tmp10;
                tmp10 = tmp10*10;
            }
            if (!deduplicationSet.contains(key)) {
                combinationsList.add(new ArrayList<>(combination));
                deduplicationSet.add(key);
            }
        }
        for (int i = idx; i < candidates.size(); i++) {
            if (remainTarget - candidates.get(i) >= 0) {
                remainTarget -= candidates.get(i);
                combination.add(candidates.get(i));
                recurCombination(candidates, remainTarget, combinationsList, combination, i+1, deduplicationSet);

                combination.remove(combination.size()-1);
                remainTarget += candidates.get(i);
            }
        }
    }

    /**
     * 来源https://leetcode-cn.com/problems/combination-sum-ii/solution/hui-su-suan-fa-jian-zhi-python-dai-ma-java-dai-m-3/
     * 剪枝来自数组去重，很精妙
     */
    public List<List<Integer>> others_combinationSum2(int[] candidates, int target) {
        int len = candidates.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

        // 关键步骤
        Arrays.sort(candidates);

        Deque<Integer> path = new ArrayDeque<>(len);
        dfs(candidates, len, 0, target, path, res);
        return res;
    }

    /**
     * @param candidates 候选数组
     * @param len        冗余变量
     * @param begin      从候选数组的 begin 位置开始搜索
     * @param target     表示剩余，这个值一开始等于 target，基于题目中说明的"所有数字（包括目标数）都是正整数"这个条件
     * @param path       从根结点到叶子结点的路径
     * @param res
     */
    private void dfs(int[] candidates, int len, int begin, int target, Deque<Integer> path, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = begin; i < len; i++) {
            // 大剪枝：减去 candidates[i] 小于 0，减去后面的 candidates[i + 1]、candidates[i + 2] 肯定也小于 0，因此用 break
            if (target - candidates[i] < 0) {
                break;
            }

            /*
              关键点在这里！！！！！！！！！！！！！！！！
              **/
            // 小剪枝：同一层相同数值的结点，从第 2 个开始，候选数更少，结果一定发生重复，因此跳过，用 continue
            if (i > begin && candidates[i] == candidates[i - 1]) {
                continue;
            }

            path.addLast(candidates[i]);
            // 调试语句 ①
            // System.out.println("递归之前 => " + path + "，剩余 = " + (target - candidates[i]));

            // 因为元素不可以重复使用，这里递归传递下去的是 i + 1 而不是 i
            dfs(candidates, len, i + 1, target - candidates[i], path, res);

            path.removeLast();
            // 调试语句 ②
            // System.out.println("递归之后 => " + path + "，剩余 = " + (target - candidates[i]));
        }
    }


    public static void main(String[] args) {
        int[] candidates = new int[]{10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        CombinationSum2_40 solution = new CombinationSum2_40();
        List<List<Integer>> res = solution.mySolution_combinationSum2(candidates, target);
        System.out.println(res);
    }
}
