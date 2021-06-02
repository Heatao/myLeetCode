package medium;

import test.TreeNode;

public class SumNumbers129 {
    /*
     * 典型的回溯
     * 但是这里不用再可以去"回"一遍，因为这里传的是int基本类型，而不是List之类的，下一层的List会影响上一层
     */
    int resultNum = 0;
    public int sumNumbers(TreeNode root) {
        int tempNum = 0;
        backTrackPreOder(root, tempNum);
        return resultNum;
    }

    private void backTrackPreOder(TreeNode node, int tempNum) {
        if (node == null)
            return;
        if (node.left == null && node.right == null) {
            resultNum = resultNum + tempNum*10 + node.val;
        }
        else {
            tempNum = tempNum*10 + node.val;
        }
        backTrackPreOder(node.left, tempNum);
        backTrackPreOder(node.right, tempNum);
    }

    public static void main(String[] args) {
        SumNumbers129 sumNumbers129 = new SumNumbers129();
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(9);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(1);
        root.right = new TreeNode(0);
        System.out.println(sumNumbers129.sumNumbers(root));
    }
}
