package easy;

import test.TreeNode;

public class HasPathSum112 {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        return findPath(root, targetSum, 0);
    }

    private boolean findPath(TreeNode node, int targetSum, int previousNum) {
        if (node == null)
            return false;
        // 下面这里画蛇了，因为val可能为负
        // if (previousNum > targetSum)
        //     return false;
        if (node.left == null && node.right == null && node.val + previousNum == targetSum) {
            return true;
        }
        return findPath(node.left, targetSum, previousNum + node.val)
                || findPath(node.right, targetSum, previousNum + node.val);
    }
}
