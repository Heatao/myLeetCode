package medium;

import test.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrder102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> levelValues = new ArrayList<>();
        if (root == null)
            return levelValues;
        Queue<TreeNode> queue = new LinkedList<>();
        //用一个len纪录每一层长度
        int len = 1;
        TreeNode node;

        queue.offer(root);
        while (!queue.isEmpty()) {
            int nextLen = 0;
            List<Integer> eachList = new ArrayList<>();

            for (int i = 0; i < len; i++) {
                node = queue.poll();
                if (node != null) {
                    eachList.add(node.val);
                    if (node.left != null) {
                        queue.offer(node.left);
                        nextLen += 1;
                    }
                    if (node.right != null) {
                        queue.offer(node.right);
                        nextLen += 1;
                    }
                }
            }
            levelValues.add(eachList);
            len = nextLen;
        }
        return levelValues;
    }
}
