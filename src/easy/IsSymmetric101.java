package easy;

import test.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class IsSymmetric101 {
    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        return mirrorTree(root.left, root.right);
    }

    private boolean mirrorTree(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null)
            return true;
        else if (t1 == null || t2 == null)
            return false;
        if (t1.val != t2.val)
            return false;
        if (mirrorTree(t1.left, t2.right) && mirrorTree(t1.right, t2.left))
            return true;
        return false;
    }

    public boolean isSymmetricNoneBack(TreeNode root) {
        if (root == null)
            return true;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);

        TreeNode n1, n2;
        while (!queue.isEmpty()) {
            n1 = queue.poll();
            n2 = queue.poll();
            if (n1 == null && n2 == null)
                continue;
            if (n1 == null || n2 == null || n1.val != n2.val)
                return false;
            queue.offer(n1.left);
            queue.offer(n2.right);
            queue.offer(n1.right);
            queue.offer(n2.left);
        }
        return true;
    }
}
