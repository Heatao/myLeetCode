package CodingInterviews;

import test.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Offer54 {
    public int kthLargest(TreeNode root, int k) {
        if (root == null || k < 0)
            throw new RuntimeException("Incorrect input data.");
        List<Integer> midOrders = new ArrayList<>();
        midOrder(midOrders, root);
        for (int i = 0; i < midOrders.size(); i++) {
            if (i == midOrders.size() - k) return midOrders.get(i);
        }
        return -1;
    }

    private void midOrder(List<Integer> midOrders, TreeNode node) {
        if (node.left != null) midOrder(midOrders, node.left);
        midOrders.add(node.val);
        if (node.right != null) midOrder(midOrders, node.right);
    }

    /**
     * krahets的解法，不需要list了
     */
    int res, k;
    public int others_kthLargest(TreeNode root, int k) {
        this.k = k;
        dfs(root);
        return res;
    }
    void dfs(TreeNode root) {
        if(root == null) return;
        dfs(root.right);
        if(k == 0) return;
        if(--k == 0) res = root.val;
        dfs(root.left);
    }

}
