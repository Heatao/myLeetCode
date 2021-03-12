package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode46.全排列
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 * 注意全排列是有顺序的，123和132不一样！
 */
public class Permute46 {
    /**
     * 下面这段代码是彻头彻尾的错误
     * 首先最重要的就是：根本没有回溯，只是在无限递归罢了
     * 后来我改好了，删除了int index，增加了回溯（remove部分）
     *
     * 优秀的题解：https://leetcode-cn.com/problems/permutations/solution/hui-su-suan-fa-python-dai-ma-java-dai-ma-by-liweiw/
     */
    public List<List<Integer>> mySolution_permute(int[] nums) {
        List<List<Integer>> combinationsList = new ArrayList<>();
        List<Integer> oneCombination = new ArrayList<>();

        arrange(nums, combinationsList, oneCombination);
        return combinationsList;
    }

    //下面用void就可以，因为java是引用类型
    //不是排列的话，可以引入参数int index
    private void arrange(int[] nums, List<List<Integer>> combinationsList, List<Integer> oneCombination) {
        if (oneCombination.size() == nums.length){
            //这里记得返回喔，而且放进总排列List的必须是new的
            combinationsList.add(new ArrayList<>(oneCombination));
            return;
        }

        //要求有顺序的话，这里i为0
        for (int num : nums) {
            //这里要求全排列，下面这个contain可以用一个used数组，以空间换时间来优化
            if (!oneCombination.contains(num)) {
                oneCombination.add(num);
                arrange(nums, combinationsList, oneCombination);
                //关键！回溯
                oneCombination.remove(oneCombination.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        Permute46 permute46 = new Permute46();
        int[] nums = {1,2,3};
        System.out.println(permute46.mySolution_permute(nums));
    }

    /**
     * 半年后回过头来看，这个题应该像官方题解那样，脑子转一下弯去理解，将其看作是把数填进去，而不是数的交换
     * 下面是powcai的题解
     */
    public List<List<Integer>> others_permute(int[] nums) {

        List<List<Integer>> res = new ArrayList<>();
        int[] visited = new int[nums.length];
        backtrack(res, nums, new ArrayList<Integer>(), visited);
        return res;

    }

    private void backtrack(List<List<Integer>> res, int[] nums, ArrayList<Integer> tmp, int[] visited) {
        if (tmp.size() == nums.length) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        //这里从0开始是其核心
        for (int i = 0; i < nums.length; i++) {
            if (visited[i] == 1) continue;
            visited[i] = 1;
            tmp.add(nums[i]);
            backtrack(res, nums, tmp, visited);
            visited[i] = 0;
            tmp.remove(tmp.size() - 1);
        }
    }
}
