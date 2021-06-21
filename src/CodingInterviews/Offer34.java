package CodingInterviews;

import test.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * 注意需要打印到叶子节点才算喔
 */
public class Offer34 {
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        List<List<Integer>> pathList = new LinkedList<>();
        if (root == null) return pathList;
        List<Integer> path = new LinkedList<>();
        findPath(pathList, path, 0, root, target);
        return pathList;
    }

    private void findPath(List<List<Integer>> pathList, List<Integer> path, int pathValue, TreeNode node, int target) {
        path.add(node.val);
        pathValue += node.val;
        if (pathValue == target && node.left == null && node.right == null) {
            pathList.add(new LinkedList<>(path));
        }
        if (node.left != null)
            findPath(pathList, path, pathValue, node.left, target);
        if (node.right != null)
            findPath(pathList, path, pathValue, node.right, target);
        // 回溯，pathValue因为是基础类型不需要减回去
        path.remove(path.size()-1);
    }
}
