package hard;

import test.TreeNode;

import java.util.Arrays;
import java.util.Collections;

/**
 * 看该题解秒懂了：https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/comments/690627
 * 这个题充分说明了，有的题（比较难的题），完全可以不按照描述的问题来理解，跳出来，换个角度去理解！
 */
public class MaxPathSum124 {
    public int maxPathSum(TreeNode root) {
        if (root == null)
            throw new RuntimeException("Incorrect input data.");
        return Math.max(firstPart(root), secondNum);
    }

    int secondNum = -1000;

    private int firstPart(TreeNode node) {
        //这里用int的最小值反而会出错，因为int的最小值加上一个值为负数的点就会溢出，然后变成正值
        if (node == null)
            return -1001;
        int valLeft, valRight;
        valLeft = firstPart(node.left);
        valRight = firstPart(node.right);
        int[] maybeList = {node.val + valLeft + valRight, valLeft, valRight};
        for (int i = 0; i < 3; i++) {
            if (secondNum < maybeList[i])
                secondNum = maybeList[i];
        }

        int firstMax = node.val;
        if (firstMax < node.val + valLeft) firstMax = node.val + valLeft;
        if (firstMax < node.val + valRight) firstMax = node.val + valRight;
        return firstMax;
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(-3);
        MaxPathSum124 maxPathSum124 = new MaxPathSum124();
        System.out.println(maxPathSum124.maxPathSum(node));
        System.out.println(maxPathSum124.do2nd(node));
        System.out.println(Integer.MIN_VALUE+1);
    }

    private int do2nd(TreeNode root) {
        // 一个节点能给其父节点提供的长度为：max(左，右)+我 ； 我
        // 当前节点可以判断的最大值为max(左+我，右+我，左+我+右，我)
        // 用后序遍历和全局变量
        if(root == null) return 0;
        backTravel(root);
        return maxPath;
    }

    int maxPath = -1001;                                                                    // 易错点：如果设置为最小值，则可能会溢出
    private int backTravel(TreeNode node) {
        int leftPath = -1001, rightPath = -1001;
        if(node.left != null) leftPath = backTravel(node.left);
        if(node.right != null) rightPath = backTravel(node.right);
        maxPath = Math.max(maxPath, rightPath + leftPath + node.val);
        maxPath = Math.max(maxPath, leftPath + node.val);
        maxPath = Math.max(maxPath, rightPath + node.val);
        maxPath = Math.max(maxPath, node.val);
        return Math.max(node.val, node.val + Math.max(leftPath, rightPath));                // 易错点：还可能是自身
    }
}
