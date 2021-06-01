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
        System.out.println(Integer.MIN_VALUE+1);
    }
}
