package CodingInterviews;

import test.TreeNode;

/**
 * 又是一道做过但是没有印象的题目，而且这道题当时一遍做对，现在我却只想到分辨判断左右子树的深度，难堪呀～
 */
public class Offer55_2 {
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        judgeBalance(root, 0);
        return balanceTag;
    }

    Boolean balanceTag = true;
    private int judgeBalance(TreeNode node, int tmpDeep) {
        if (node == null) return tmpDeep;
        tmpDeep++;
        int leftDep = judgeBalance(node.left, tmpDeep);
        int rightDep = judgeBalance(node.right, tmpDeep);
        if (Math.abs(leftDep - rightDep) > 1)
            balanceTag = false;
        // 第二次做，这里return错了
        return Math.max(leftDep, rightDep);
        // return tmpDeep;
    }
}
