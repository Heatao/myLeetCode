package easy;

import test.TreeNode;

/**
 * 需要注意判断下叶子节点
 */
public class MinDepth111 {
    //下面这个用null来判断叶子，其实不是叶子哟
    public int minDepth(TreeNode root) {
        if (root == null)
            return 0;
        int leftDeep = minDepth(root.left);
        int rightDeep = minDepth(root.right);
        return Math.min(leftDeep, rightDeep) + 1;
    }

    public int minDepth_twice(TreeNode root) {
        if (root == null)
            return 0;
        if (root.left == null && root.right == null)
            return 1;

        int leftDeep = Integer.MAX_VALUE, rightDeep = Integer.MAX_VALUE;
        if (root.left != null)
            leftDeep = minDepth_twice(root.left);
        if (root.right != null)
            rightDeep = minDepth_twice(root.right);
        return Math.min(leftDeep, rightDeep) + 1;
    }
}
