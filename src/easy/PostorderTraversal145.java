package easy;

import test.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class PostorderTraversal145 {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> postValues = new ArrayList<>();
        if (root == null)
            return postValues;
        Deque<Object> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Object ele = stack.pop();
            if (ele instanceof TreeNode) {
                TreeNode node = (TreeNode) ele;
                stack.push(node.val);
                if (node.right != null) stack.push(node.right);
                if (node.left != null) stack.push(node.left);
            }
            else postValues.add((Integer) ele);
        }

        return postValues;
    }
}
