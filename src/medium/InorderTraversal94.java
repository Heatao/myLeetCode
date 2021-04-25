package medium;

import test.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 先序：考察到一个节点后，即刻输出该节点的值，并继续遍历其左右子树。(根左右)
 * 中序：考察到一个节点后，将其暂存，遍历完左子树后，再输出该节点的值，然后遍历右子树。(左根右)
 * 后序：考察到一个节点后，将其暂存，遍历完左右子树后，再输出该节点的值。(左右根)
 *
 * 递归的方式：
 * 先序：NLR
 * 中序：LNR
 * 后序：LRN
 *
 * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/solution/yan-se-biao-ji-fa-yi-chong-tong-yong-qie-jian-ming/
 * 颜色标记法的入栈顺序：
 * 先序：RLN
 * 中序：RNL
 * 后序：NRL
 */
public class InorderTraversal94 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        inorderTraversalBackTrack(root, ans);
        return ans;
    }

    public void inorderTraversalBackTrack(TreeNode node, List<Integer> ans) {
        if (node != null) {
            inorderTraversalBackTrack(node.left, ans);
            ans.add(node.val);
            inorderTraversalBackTrack(node.right, ans);
        }
    }

    /**
     * 一般来说用非递归是为了降低复杂度，因为递归的方式有递归栈，而下面的方式在LC提交反而不如递归，
     * 猜测原因要么是数据不够大造成的偏差，要么是因为下面是一种模拟递归的方式，所以效率并不高
     */
    public List<Integer> inorderTraversalNonRecursive(TreeNode root) {
        List<Integer> visitOrder = new ArrayList<>();
        if (root == null)
            return visitOrder;

        Object colorNode;
        Deque<Object> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            colorNode = stack.pop();
            //如果是第一次遇见
            //下面除了colorNode.getClass().isInstance(TreeNode.class)还可以用instanceof关键字
            if (colorNode instanceof TreeNode) {
                TreeNode treeNode = (TreeNode)colorNode;
                if (treeNode.right != null) stack.push(treeNode.right);
                stack.push(treeNode.val);
                if (treeNode.left != null) stack.push(treeNode.left);
            }
            else {
                visitOrder.add((Integer) colorNode);
            }
        }
        return visitOrder;
    }
}
