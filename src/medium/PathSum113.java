package medium;

import test.TreeNode;

import java.util.LinkedList;
import java.util.List;

public class PathSum113 {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> pathList = new LinkedList<>();
        List<Integer> path = new LinkedList<>();
        findPath(root, targetSum, 0, pathList, path);
        return pathList;
    }

    private void findPath(TreeNode node, int targetSum, int previousNum, List<List<Integer>> pathList, List<Integer> path) {
        if (node == null)
            return;
        path.add(node.val);
        if (node.left == null && node.right == null && node.val + previousNum == targetSum) {
            pathList.add(new LinkedList<>(path));
        }
        else {
            findPath(node.left, targetSum, previousNum + node.val, pathList, path);
            findPath(node.right, targetSum, previousNum + node.val, pathList, path);
        }
        //这里可以换成Deque，这样可以用pollLast
        path.remove(path.size()-1);
    }
}
