package medium;

import test.TreeNode;

import java.util.*;

public class ZigzagLevelOrder103 {
    //基本的思路是层次遍历，然后每隔一层reverse一下
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> levelValues = new ArrayList<>();
        if (root == null)
            return levelValues;

        boolean reverseTag = false;
        int len = 1;
        int nextLen = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> eachLevel = new ArrayList<>();
            for (int i = 0; i < len; i++) {
                TreeNode node = queue.poll();
                eachLevel.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                    nextLen += 1;
                }
                if (node.right != null) {
                    queue.offer(node.right);
                    nextLen += 1;
                }
            }
            len = nextLen;
            nextLen = 0;
            if (reverseTag) {
                Collections.reverse(eachLevel);
                reverseTag = false;
            }
            else reverseTag = true;
            levelValues.add(eachLevel);
        }
        return levelValues;
    }
}
