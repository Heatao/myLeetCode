package CodingInterviews;

import test.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Offer32_2 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> outerList = new ArrayList<>();
        if (root == null) return outerList;

        List<Integer> levelList = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int len;
        while (!queue.isEmpty()) {
            len = queue.size();
            levelList.clear();
            for (int i = 0; i < len; i++) {
                TreeNode node = queue.poll();
                levelList.add(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            outerList.add(new ArrayList<>(levelList));
        }
        return outerList;
    }
}
