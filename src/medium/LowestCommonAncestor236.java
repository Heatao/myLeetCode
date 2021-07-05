package medium;

import test.TreeNode;

/**
 * 值得再做一遍！
 */
public class LowestCommonAncestor236 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return null;
        if(p == root || q == root) return root;
        TreeNode leftNode = lowestCommonAncestor(root.left, p, q);
        TreeNode rightNode = lowestCommonAncestor(root.right, p, q);
        if(leftNode != null && rightNode != null) return root;
        if(leftNode == null) return rightNode;
        if(rightNode == null) return leftNode;
        return null;
    }
}
