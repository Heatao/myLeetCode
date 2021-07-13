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

    /**
     * 当年一次做对，现在则错了一点点
     * 之前的做法是自底向上，类似于后序遍历，现在0713的做法是自顶向下，是一种直观的思路
     */
    private boolean do2nd(TreeNode root) {
        if(root == null) return true;
        findDep(root, 0);
        return tag;
    }

    // 注意只有左右子树都为空才是叶子哟
    boolean tag = true;
    private int findDep(TreeNode node, int nowDep) {
        nowDep += 1;
        int leftD = nowDep, rightD = nowDep;                                // 易错点，不能初始化为0，而应该初始化为当前深度
        if(node.left != null) leftD = findDep(node.left, nowDep);
        if(node.right != null) rightD = findDep(node.right, nowDep);
        if(Math.abs(leftD - rightD) > 1) tag = false;

        if(node.left == null && node.right == null)
            return nowDep;
        else return Math.max(leftD, rightD);
    }
}
