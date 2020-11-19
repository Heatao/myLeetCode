package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode39.组合总和
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的数字可以无限制重复被选取。
 * 说明：
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。 
 * 提示：
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * candidate 中的每个元素都是独一无二的。
 * 1 <= target <= 500
 */
public class CombinationSum39 {
    /**
     * 第一反应和背包问题很像，想用DP处理，回去看DP的笔记发现这是比较容易混淆的题型，遇到所有组合其实回溯就可以
     * 这题也可以用DP，除了DP表以外，再存一个字典，value是list的也可以
     * 参考https://leetcode-cn.com/problems/combination-sum/solution/hui-su-suan-fa-jian-zhi-python-dai-ma-java-dai-m-2/
     *
     *
     * ### 理解回溯
     * 与动态规划的区别：
     * 共同点
     * 用于求解多阶段决策问题。多阶段决策问题即：
     * 求解一个问题分为很多步骤（阶段）；
     * 每一个步骤（阶段）可以有多种选择。
     *
     * 不同点
     * 动态规划只需要求我们评估最优解是多少，最优解对应的具体解是什么并不要求。因此很适合应用于评估一个方案的效果；
     * 回溯算法可以搜索得到所有的方案（当然包括最优解），但是本质上它是一种遍历算法，时间复杂度很高。
     * 作者：liweiwei1419
     * 链接：https://leetcode-cn.com/problems/permutations/solution/hui-su-suan-fa-python-dai-ma-java-dai-ma-by-liweiw/
     */
    public List<List<Integer>> mySolution_combinationSum(int[] candidates, int target) {
        List<List<Integer>> combinationsResult = new ArrayList<List<Integer>>();
        List<Integer> combination = new ArrayList<>();
        recurCombination(candidates, target, combinationsResult, combination,0);
        return combinationsResult;
    }

    /**
     * idx记录开始的位置
     * */
    private void recurCombination(int[] candidates, int remainTarget, List<List<Integer>> combinationsResult,
                                 List<Integer> combination, int idx) {
        //下面这两行没有必要存在
//        if (idx == candidates.length)
//            return;
        if (remainTarget == 0) {
            ///区别在于这里没有用combination的空间，因为传参的combination列表是复用的
            combinationsResult.add(new ArrayList<>(combination));
            return;
        }
        for (int i = idx; i < candidates.length; i++) {
            if (remainTarget-candidates[i] >= 0){
                combination.add(candidates[i]);
                recurCombination(candidates, remainTarget-candidates[i], combinationsResult, combination, i);
                //下面这一句是精髓，"回溯"！
                combination.remove(combination.size()-1);
            }
        }
    }
}
