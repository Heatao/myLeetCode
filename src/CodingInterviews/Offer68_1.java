package CodingInterviews;

import test.TreeNode;

public class Offer68_1 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val > q.val) {
            TreeNode tmp = p;
            p = q;
            q = tmp;
        }
        return backTrack(root, p, q);
    }

    private TreeNode backTrack(TreeNode node, TreeNode p, TreeNode q) {
        if (node.val >= p.val && node.val <= q.val)
            return node;
        else if (node.val > q.val) return backTrack(node.left, p, q);
        else return backTrack(node.right, p, q);
    }
}
