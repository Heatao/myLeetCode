package easy;

import test.TreeNode;

public class MaxDepth104 {
    int deep = 0;
    public int maxDepth(TreeNode root) {
        preOrder(root, 0);
        return deep;
    }

    private void preOrder(TreeNode node, int tmpDeep) {
        if (node == null)
            return;
        tmpDeep += 1;
        deep = Math.max(tmpDeep, deep);
        if (node.left != null)
            preOrder(node.left, tmpDeep);
        //tmpDeep是基本类型，下一层递归中被改变的部分不会影响当前
        if (node.right != null)
            preOrder(node.right, tmpDeep);
    }
}
