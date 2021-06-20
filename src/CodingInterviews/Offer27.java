package CodingInterviews;

import test.TreeNode;

/**
 * 还可以用栈或者队列来做喔~
 */
public class Offer27 {
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) return null;
        TreeNode tmp = root.right;
        root.right = root.left;
        root.left = tmp;
        mirrorTree(root.left);
        mirrorTree(root.right);
        return root;
    }
}
