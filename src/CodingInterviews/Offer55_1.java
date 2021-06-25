package CodingInterviews;

import test.TreeNode;

public class Offer55_1 {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        preOrder(root, 0);
        return deep;
    }

    int deep;
    private void preOrder(TreeNode node, int tmpDeep) {
        tmpDeep++;
        deep = Math.max(tmpDeep, deep);
        if (node.left != null) preOrder(node.left, tmpDeep);
        if (node.right != null) preOrder(node.right, tmpDeep);
    }
}
