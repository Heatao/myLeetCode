package medium;

import test.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class IsValidBST98 {
    public boolean isValidBST(TreeNode root) {
        //用颜色标记法中序遍历
        long last = Long.MIN_VALUE;
        Deque<Object> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Object node = stack.pop();
            // 为什么lc不能用下面这种呢？
            // if (node.getClass().isInstance(TreeNode.class)) {
            if (node instanceof TreeNode) {
                TreeNode treeNode = (TreeNode) node;
                if (treeNode.right != null) stack.push(treeNode.right);
                stack.push(treeNode.val);
                if (treeNode.left != null) stack.push(treeNode.left);
            }
            else {
                Long intNode = ((Integer) node).longValue();
                if (intNode <= last) {
                    return false;
                }
                else last = intNode;
            }
        }
        return true;
    }
}
