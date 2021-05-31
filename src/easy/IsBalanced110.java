package easy;

import test.TreeNode;

public class IsBalanced110 {
    public boolean isBalanced(TreeNode root) {
        judgeBalance(root);
        return balanced;
    }

    boolean balanced = true;
    private int judgeBalance(TreeNode node) {
        if (node == null)
            return 0;
        int leftDeep = judgeBalance(node.left);
        int rightDeep = judgeBalance(node.right);
        if (Math.abs(leftDeep - rightDeep) > 1)
            balanced = false;
        return Math.max(leftDeep, rightDeep) + 1;
    }
}
