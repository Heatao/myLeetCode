package CodingInterviews;

import test.TreeNode;

import java.util.*;

public class Offer32_1 {
    public int[] levelOrder(TreeNode root) {
        if (root == null) return new int[]{};
        List<Integer> levelList = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            levelList.add(node.val);
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }

        return levelList.stream().mapToInt(Integer::intValue).toArray();
    }
}
