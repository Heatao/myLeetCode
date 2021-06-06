package easy;

import test.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class PreorderTraversal144 {
    /*
    教学：Deque当stack用的时候，用push和pop，而不是add和remove
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> preValues = new ArrayList<>();
        if (root == null)
            return preValues;
        Deque<Object> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Object ele = stack.pop();
            if (ele instanceof TreeNode) {
                TreeNode node = (TreeNode) ele;
                if (node.right != null) stack.push(node.right);
                if (node.left != null) stack.push(node.left);
                stack.push(node.val);
            }
            else preValues.add((Integer) ele);
        }

        return preValues;
    }

    public List<Integer> preorderTraversalRecur(TreeNode root) {
        List<Integer> preValues = new ArrayList<>();
        if (root == null)
            return preValues;
        recur(root, preValues);
        return preValues;
    }

    private void recur(TreeNode node, List<Integer> preValues) {
        if (node == null) return;
        preValues.add(node.val);
        recur(node.left, preValues);
        recur(node.right, preValues);
    }
}
