package CodingInterviews;

import test.TreeNode;
import java.util.*;

/**
 * 采用头插法和尾插法的话效率会更高一点
 */
public class Offer32_3 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> outerList = new ArrayList<>();
        if (root == null) return outerList;

        List<Integer> levelList = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int len;
        boolean tag = true;
        while (!queue.isEmpty()) {
            len = queue.size();
            levelList.clear();
            for (int i = 0; i < len; i++) {
                TreeNode node = queue.poll();
                levelList.add(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            if (!tag) {
                Collections.reverse(levelList);
                tag = true;
            }
            else tag = false;
            outerList.add(new ArrayList<>(levelList));
        }
        return outerList;
    }
}
