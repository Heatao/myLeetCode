package medium;

import test.TreeNode;

public class RecoverTree99 {
    //因为没有说非要交换节点，所以可以交换val
    //为了熟悉非递归，还是用颜色交换法吧
    //最后发现不能用颜色交换法，因为需要用到当前node，而颜色交换法存的是val
    public void recoverTree(TreeNode root) {
        medTraverse(root, null, null);
    }

    //下面这个是错误的，因为传入preval并非真正的"前一个"
    //最好的办法是用类里的全局变量，不要吝啬用全局变量！
    private void medTraverse(TreeNode node, Integer preVal, TreeNode badNode) {
        if (node.left != null) medTraverse(node.left, preVal, badNode);
        if (preVal == null)
            preVal = node.val;
        else {
            if (preVal > node.val) {
                if (badNode == null)
                    badNode = node;
                else {
                    int temp = badNode.val;
                    badNode.val = node.val;
                    node.val = temp;
                }
            }
        }
        if (node.right != null) medTraverse(node.right, preVal, badNode);
    }

    TreeNode firstNode = null;
    TreeNode secondNode = null;
    TreeNode prevNode;
    public void recoverTreeRefOthers(TreeNode root) {
        inorder(root);
        int tmp = firstNode.val;
        firstNode.val = secondNode.val;
        secondNode.val = tmp;
    }
    private void inorder(TreeNode node) {
        if (node == null) return;
        inorder(node.left);
        //中序遍历，结果中如果有一个降序对，说明该两个node需交换；若有两个降序对，说明第一对的前一个node和第二对的后一个node需要交换。
        if (prevNode != null && prevNode.val > node.val) {
            if (firstNode == null) firstNode = prevNode;
            secondNode = node;
        }
        prevNode = node;
        inorder(node.right);
    }
}
