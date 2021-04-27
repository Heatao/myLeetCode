package medium;

import test.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * 二叉搜索树，二叉查找树，二叉排序树：节点的左子树的所有节点的值小于它，它小于右子树
 */
public class GenerateTrees95 {
    /*
    这种需要返回组合的题一般来说是回溯

    下面这个思路是一般的回溯思路，但是这样的回溯很容易出现重复解，如下面的题解所说哒
    https://leetcode-cn.com/problems/unique-binary-search-trees-ii/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-2-7/
     */
    @Deprecated
    public List<TreeNode> generateTrees_old(int n) {
        List<TreeNode> trees = new LinkedList<>();
        if (n == 0)
            return trees;

        for (int i = 0; i < n; i++) {
            TreeNode nowNode = new TreeNode(i);
            backtrackTree(n, trees, nowNode, 0);
        }
        return trees;
    }

    @Deprecated
    private void backtrackTree(int n, List<TreeNode> trees, TreeNode nowNode, int index) {
        //
    }

    public List<TreeNode> generateTrees(int n) {
        if (n == 0)
            return new LinkedList<TreeNode>();
        return generateTrees(1, n);                         //这里是左闭右闭喔
    }

    /**
     * 下面这个解法思想在于，1-n是排好序的，而二叉排序树就是i的左边组合为左子树，右边组合为右子树
     */
    private List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> allTrees = new LinkedList<>();
        if (start > end) {
            allTrees.add(null);
            return allTrees;
        }

        // 枚举可行根节点
        for (int i = start; i <= end; i++) {
            //因为返回的是一个列表，所以子树的组合也用列表
            //之前的通用做法是维护一个总的combinations，而这里类似子列表组合起来

            // 获得所有可行的左子树集合
            List<TreeNode> leftTrees = generateTrees(start, i - 1);
            // 获得所有可行的右子树集合
            List<TreeNode> rightTrees = generateTrees(i + 1, end);

            // 从左子树集合中选出一棵左子树，从右子树集合中选出一棵右子树，拼接到根节点上
            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode nowNode = new TreeNode(i);
                    nowNode.left = left;
                    nowNode.right = right;
                    allTrees.add(nowNode);
                }
            }
        }
        return allTrees;
    }
}
