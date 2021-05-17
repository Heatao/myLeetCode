package medium;

import test.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderBottom {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> levelOrders = new LinkedList<>();
        if (root == null)
            return levelOrders;
        int len = 1;
        int newLen = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new LinkedList<>();
            for (int i = 0; i < len; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                    newLen++;
                }
                if (node.right != null) {
                    queue.offer(node.right);
                    newLen++;
                }
            }
            levelOrders.add(0, level);
            len = newLen;
            newLen = 0;
        }
        return levelOrders;
    }
}
