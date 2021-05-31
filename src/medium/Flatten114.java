package medium;

import test.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 如果考虑O(1)的额外空间，就挺难的，不然可以直接用一个List去存
 */
public class Flatten114 {
    public void flatten(TreeNode root) {
        if (root == null)
            return;
        List<TreeNode> nodeList = new ArrayList<>();
        preOrder(root, nodeList);
        for (int i = 0; i <= nodeList.size()-2; i++) {
            nodeList.get(i).right = nodeList.get(i+1);
            nodeList.get(i).left = null;
        }
        nodeList.get(nodeList.size()-1).right = null;
        nodeList.get(nodeList.size()-1).left = null;
    }

    private void preOrder(TreeNode node, List<TreeNode> nodeList) {
        if (node == null)
            return;
        nodeList.add(node);
        preOrder(node.left, nodeList);
        preOrder(node.right, nodeList);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        Flatten114 flatten114 = new Flatten114();
        flatten114.preOrderTree(root);
        flatten114.flatten(root);
        flatten114.preOrderTree(root);
    }

    private void preOrderTree(TreeNode node) {
        if (node == null) {
            System.out.println("null");
            return;
        }
        System.out.println(node.val);
        preOrderTree(node.left);
        preOrderTree(node.right);
    }

    public void official_flatten(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left != null) {
                TreeNode next = curr.left;
                TreeNode predecessor = next;
                while (predecessor.right != null) {
                    predecessor = predecessor.right;
                }
                predecessor.right = curr.right;
                curr.left = null;
                curr.right = next;
            }
            curr = curr.right;
        }
    }
}
