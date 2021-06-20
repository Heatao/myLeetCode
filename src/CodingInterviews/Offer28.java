package CodingInterviews;

import test.TreeNode;

/**
 * 我的思路：
 * 1. 层序遍历+判断回文
 * 2. 一起遍历
 */
public class Offer28 {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return judgeMirror(root.left, root.right);
    }

    private boolean judgeMirror(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) return true;
        if (node1 == null || node2 == null) return false;
        if (node1.val == node2.val) {
            return judgeMirror(node1.left, node2.right) && judgeMirror(node1.right, node2.left);
        }
        else return false;
    }
}
